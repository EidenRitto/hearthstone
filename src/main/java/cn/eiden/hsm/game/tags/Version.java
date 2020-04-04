package cn.eiden.hsm.game.tags;

/**
 * 版本
 * @author 周晋平
 * @date 2020/4/4 12:09
 */
public enum Version {
    /**基础*/
    BASE("基础","base"),
    /**经典*/
    CLASSIC("经典","classic")
    ;

    Version(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    private String name;
    private String packageName;

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }
}
