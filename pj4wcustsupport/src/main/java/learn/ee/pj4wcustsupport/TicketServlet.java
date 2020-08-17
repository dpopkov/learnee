package learn.ee.pj4wcustsupport;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@WebServlet(
        name = "ticketServlet",
        urlPatterns = {"/tickets"},
        loadOnStartup = 1
)
@MultipartConfig(
        fileSizeThreshold = 5_242_880, // 5MB - how big the file has to be before it is written to the temp directory
        maxFileSize = 20_971_520L, // 20MB - prohibits an uploaded file from exceeding 20 megabytes
        maxRequestSize = 41_943_040L // 40MB - prohibits the total size of a request from exceeding 40 megabytes
)
public class TicketServlet extends HttpServlet {
    private volatile int ticketIdSequence = 1;
    private final Map<Integer, Ticket> ticketDatabase = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("login");
            return;
        }
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "create":
                showTicketForm(req, resp);
                break;
            case "view":
                viewTicket(req, resp);
                break;
            case "download":
                downloadAttachment(req, resp);
                break;
            case "list":
            default:
                listTickets(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("login");
            return;
        }
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "create":
                createTicket(req, resp);
                break;
            case "download":
            default:
                resp.sendRedirect("tickets");
                break;
        }
    }

    /** Shows a form for creating a new ticket. */
    private void showTicketForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/ticketForm.jsp").forward(req, resp);
    }

    /** Sends a ticket's info with attachments (if any) to the response. */
    private void viewTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idString = req.getParameter("ticketId");
        Ticket ticket = getTicket(idString, resp);
        if (ticket == null) {
            return;
        }
        req.setAttribute("ticketId", idString);
        req.setAttribute("ticket", ticket);
        req.getRequestDispatcher("/WEB-INF/jsp/view/viewTicket.jsp").forward(req, resp);
    }

    /** Sends the attachment's bytes to the response. */
    private void downloadAttachment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idString = req.getParameter("ticketId");
        Ticket ticket = getTicket(idString, resp);
        if (ticket == null) {
            return;
        }
        String name = req.getParameter("attachment");
        if (name == null) {
            resp.sendRedirect("tickets?action=view&ticketId=" + idString);
            return;
        }
        Attachment attachment = ticket.getAttachment(name);
        if (attachment == null) {
            resp.sendRedirect("tickets?action=view&ticketId=" + idString);
            return;
        }
        resp.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        resp.setContentType("application/octet-stream");
        ServletOutputStream stream = resp.getOutputStream();
        stream.write(attachment.getContents());
    }

    /** Lists all the tickets in the database. */
    private void listTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("ticketDatabase", ticketDatabase);
        req.getRequestDispatcher("/WEB-INF/jsp/view/listTickets.jsp").forward(req, resp);
    }

    /** Creates ticket from the request parameters and adds it to the database. */
    private void createTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName((String) req.getSession().getAttribute("username"));
        ticket.setSubject(req.getParameter("subject"));
        ticket.setBody(req.getParameter("body"));
        Part filePart = req.getPart("file1");
        if (filePart != null && filePart.getSize() > 0) {
            Attachment attachment = processAttachment(filePart);
            ticket.addAttachment(attachment);
        }
        int id;
        synchronized (this) {
            id = ticketIdSequence++;
            ticketDatabase.put(id, ticket);
        }
        resp.sendRedirect("tickets?action=view&ticketId=" + id);
    }

    /** Copies bytes from the multi-part request to the attachment object. */
    private Attachment processAttachment(Part filePart) throws IOException {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int read;
        final byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        Attachment attachment = new Attachment();
        attachment.setName(filePart.getSubmittedFileName());
        attachment.setContents(outputStream.toByteArray());
        return attachment;
    }

    private Ticket getTicket(String idString, HttpServletResponse resp) throws IOException {
        if (idString == null || idString.length() == 0) {
            resp.sendRedirect("tickets");
            return null;
        }
        try {
            Ticket ticket = ticketDatabase.get(Integer.parseInt(idString));
            if (ticket == null) {
                resp.sendRedirect("tickets");
                return null;
            }
            return ticket;
        } catch (Exception e) {
            resp.sendRedirect("tickets");
            return null;
        }
    }
}
