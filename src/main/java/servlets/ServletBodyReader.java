package servlets;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletBodyReader {
    private static Logger logger = Logger.getLogger(UpdateServlet.class.getName());

    public String readJsonStringFromRequest(HttpServletRequest request) {
        final StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            if (reader == null) {
                logger.log(Level.SEVERE, "Request body could not be read because it's empty.");
            }
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Could not obtain the same request body from the http request", e);
        }
        return builder.toString();
    }
}
