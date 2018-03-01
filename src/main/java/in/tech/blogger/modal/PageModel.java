package in.tech.blogger.modal;


public class PageModel {

    Integer max = 10;

    Integer page = 1;

    public PageModel() {
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getPage() {
        if (page < 1) {
            return 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
