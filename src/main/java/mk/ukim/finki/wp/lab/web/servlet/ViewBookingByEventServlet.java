package mk.ukim.finki.wp.lab.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.ViewBookingByEventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewBookingByEventServlet", urlPatterns = "/viewBookingByEvent")
public class ViewBookingByEventServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final ViewBookingByEventService viewBookingByEventService;

    public ViewBookingByEventServlet(SpringTemplateEngine springTemplateEngine, ViewBookingByEventService viewBookingByEventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.viewBookingByEventService = viewBookingByEventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        String eventName = req.getParameter("eventName");

        WebContext context = new WebContext(webExchange);

        List<EventBooking> eventBookings= new ArrayList<>();
        if (eventName != null) {
            eventBookings = viewBookingByEventService.findBookingByEventName(eventName);
        }

        context.setVariable("eventBookings", eventBookings);
        springTemplateEngine.process("test.html",context, resp.getWriter());
    }
}
