package mk.ukim.finki.wp.lab.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService eventBookingService;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, SpringTemplateEngine springTemplateEngine1, EventBookingService eventBookingService) {
        this.springTemplateEngine = springTemplateEngine1;
        this.eventBookingService = eventBookingService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        String eventName = req.getParameter("eventName");
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));

        EventBooking booking = eventBookingService.placeBooking(attendeeName, attendeeAddress, eventName, numberOfTickets);

        context.setVariable("booking", booking);

        springTemplateEngine.process("/bookingConfirmation.html",context, resp.getWriter());
    }
}


