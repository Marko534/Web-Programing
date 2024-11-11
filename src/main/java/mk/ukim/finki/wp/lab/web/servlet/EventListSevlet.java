package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "EventListServlet", urlPatterns = {"/eventList", "/searchEvents", ""})
public class EventListSevlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    public EventListSevlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        String searchTerm = req.getParameter("searchTerm");
        String minScoreParam = req.getParameter("minScore");

        double minScore;
        if (minScoreParam != null && !minScoreParam.isEmpty()) {
            minScore = Double.parseDouble(minScoreParam);
        } else {
            minScore = 0.0;
        }

        List<Event> events = new ArrayList<>(); //

        events = (searchTerm == null || searchTerm.isEmpty())
                ? eventService.listAll()
                : eventService.searchEvents(searchTerm, 0.0);

        events = events.stream()
                .filter(x -> x.getPopularityScore() >= minScore)
                .collect(Collectors.toList());

        WebContext context = new WebContext(webExchange);
        context.setVariable("events", events);

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
