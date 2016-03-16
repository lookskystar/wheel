package com.lundui.manage.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 轴箱组装日期到期提醒标签
 * @author long
 *
 */
public class AlertTag extends SimpleTagSupport {
	
	/**
	 * 组装日期
	 */
	private Date createDate;
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		JspContext ctx = getJspContext();
		JspWriter out = ctx.getOut();
		
		Date start = createDate;
		if(createDate == null){
			out.print("");
		}else{
			Calendar sc = Calendar.getInstance();
			sc.setTime(start);
			
			Date now = new Date();
			Calendar nc = Calendar.getInstance();
			nc.setTime(now);
			
			int result = (nc.get(Calendar.YEAR) - sc.get(Calendar.YEAR))*12 + 
					nc.get(Calendar.MONTH) - sc.get(Calendar.MONTH);
			if(result >= 5){
				out.print("快到期了");
			}else{
				out.print("正常");
			}
		}
	}
	
}
