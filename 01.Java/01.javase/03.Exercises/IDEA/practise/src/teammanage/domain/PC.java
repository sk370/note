package teammanage.domain;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:07
 */
public class PC implements Equipment{
    private String model;
    private String display;
    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public String getModel() {
        return model;
    }
    public String getDisplay() {
        return display;
    }
    @Override
    public String getDescription() {
        return model + "(" + display + ")";
    }
}
