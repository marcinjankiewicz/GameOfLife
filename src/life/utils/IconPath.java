package life.utils;

public enum IconPath {
    RUN("resources/images/right-arrow.png"),
    PAUSE("resources/images/pause_icon.png"),
    REFRESH("resources/images/refresh-button.png");

    private String path;

    IconPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
