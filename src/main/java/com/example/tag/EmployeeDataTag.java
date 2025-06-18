package com.example.tag;

import com.example.entity.Employee;
import com.example.locale.Message;
import com.example.locale.MessageLocale;
import com.example.locale.MessageUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class EmployeeDataTag extends TagSupport {
    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(showEmployeeData());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    private String showEmployeeData() {
        return new StringBuffer().append(MessageLocale.BUNDLE.getString(Message.LOGGED_IN_AS)).append(employee.getPhone())
                .append(MessageUtils.LEFT_PARENTHESIS)
                .append(MessageLocale.BUNDLE.getString(employee.getRole().getLocalizedValue()))
                .append(MessageUtils.RIGHT_PARANTHESIS).toString();
    }
}
