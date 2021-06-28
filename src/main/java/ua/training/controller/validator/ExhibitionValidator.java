package ua.training.controller.validator;

import ua.training.model.entity.Exhibition;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ua.training.constants.RegularExpressions.*;

public class ExhibitionValidator {

    public Optional<Exhibition> validate(HttpServletRequest request) {
        LocalDate ld;
        String error;
        Map<String, String> map = new HashMap<>();
        String theme = request.getParameter("theme");
        String themeUk = request.getParameter("themeUk");
        String description = request.getParameter("description");
        String descriptionUk = request.getParameter("descriptionUk");
        String imageUrl = request.getParameter("imageUrl");
        String date = request.getParameter("date");
        String price = request.getParameter("price");
        map.put(theme, EN_REGEX);
        map.put(themeUk, UA_REGEX);
        map.put(description, EN_REGEX);
        map.put(descriptionUk, UA_REGEX);
        map.put(imageUrl, URL_REGEX);
        map.put(date, DATE_REGEX);
        map.put(price, PRICE_REGEX);
        for (String str : map.keySet()) {
            if (str == null || !str.matches(map.get(str))) {
                if (str == date) {
                    error = "invalidDate";
                } else {
                    error = str;
                }
                request.getSession().setAttribute("error", error);
                return Optional.empty();
            }
        }
        ld = LocalDate.parse(date);
        if (ld.isBefore(LocalDate.now())) {
            error = "invalidDate";
            request.getSession().setAttribute("error", error);
            return Optional.empty();
        }
        Exhibition exhibition = Exhibition.builder()
                .themeUk(themeUk)
                .descriptionUk(descriptionUk)
                .description(description)
                .theme(theme)
                .date(ld)
                .price(new BigDecimal(price))
                .imageUrl(imageUrl)
                .build();
        return Optional.of(exhibition);
    }
}
