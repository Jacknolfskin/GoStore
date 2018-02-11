package cn.zhx.common.page;

import java.util.List; /**
 * Created by 胡飞 on 2017/7/20.
 */
/*
 * 通用分页封装类
 */
public class Pagination {
    private int pageSize;// 每页大小
    private int totleSize;// 总共多少条
    private int totlePage;// 总共多少页
    private int pageIndex;// 当前第几页
    private String url;// 提交到的页面


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotleSize() {
        return totleSize;
    }

    public void setTotleSize(int totleSize) {
        this.totleSize = totleSize;
    }

    // 计算出总共多少页
    public int getTotlePage() {
        totlePage = totleSize / pageSize;
        if (totleSize % pageSize != 0) {
            totlePage++;

        }
        return totlePage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageBar() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div align='center'>");
        sb.append("<form action='" + getUrl() + "' method='get'>");
        // 一系列的判断逻辑
        // 什么时候有首页链接
        if (pageIndex == 1) {

            sb.append("首页");
        } else {

            sb.append("<a href='" + getUrl() + "?index=1'>首页</a>");
        }

        // 什么时候显示上一页链接

        if (pageIndex == 1) {

            sb.append("上一页  ");
        } else {
            int temp = pageIndex;
            sb.append("<a href='" + getUrl() + "?index=" + (temp - 1)
                    + "'>上一页</a>");
        }

        // 什么时候显示下一页
        if (getTotlePage() == pageIndex) {
            sb.append("下一页");
        } else {
            int temp = pageIndex;
            sb.append("<a href='" + getUrl() + "?index=" + (temp + 1)
                    + "'>下一页</a>");

        }

        // 什么时候显示尾页呢
        if (getTotlePage() == pageIndex) {
            sb.append("尾页");
        } else {
            sb.append("<a href='" + getUrl() + "?index=" + getTotlePage()
                    + "'>尾页</a>");

        }

        sb.append("<input type='text' name='index' style='width: 20px' value='"
                + pageIndex + "'  /> <input type='submit' value='到达' />");

        sb.append("</form>");
        sb.append("</div>");

        return sb.toString();

    }

}