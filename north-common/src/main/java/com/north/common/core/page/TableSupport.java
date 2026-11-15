package com.north.common.core.page;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class TableSupport {
    private TableSupport() {}

    public static PageDomain buildPageRequest() {
        PageDomain pageDomain = new PageDomain();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) return pageDomain;

        HttpServletRequest request = requestAttributes.getRequest();

        pageDomain.setPageNum(parsePositiveInt(request.getParameter("pageNum"), 1));
        pageDomain.setPageSize(parsePositiveInt(request.getParameter("pageSize"), 10));

        String reasonable = request.getParameter("reasonable");
        if (reasonable != null) {
            pageDomain.setReasonable(Boolean.parseBoolean(reasonable));
        }


        return pageDomain;
    }

    private static int parsePositiveInt(String value, int defaultValue) {
        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        try {
            int number = Integer.parseInt(value);
            return number > 0 ? number : defaultValue;
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }
}
