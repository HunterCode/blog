package com.lion.blog.bean;


import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
    private List<ArticlesDTO> articles;
    private List<UsersDTO> users;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNextPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.page = page;
        Integer totalPage;
        if(totalCount == 0) {
            return ;
        }
        if(totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        this.totalPage = totalPage;
        // 显示页码
        pages.add(page);
        for(int i = 1; i <= 3; i++) {
            if(page - i > 0) {
                pages.add(0, page - i);
            }
            if(page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        if(page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        // 是否展示下一页
        if(page == totalPage) {
            showNextPage = false;
        } else {
            showNextPage = true;
        }
        // 是否展示首页
        if(pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        // 是否展示尾页
        if(pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }

    public List<ArticlesDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesDTO> articles) {
        this.articles = articles;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNextPage() {
        return showNextPage;
    }

    public void setShowNextPage(boolean showNextPage) {
        this.showNextPage = showNextPage;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<UsersDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UsersDTO> users) {
        this.users = users;
    }
}
