package cn.eiden.hsm.game.rule;

/**
 * @author Eiden J.P Zhou
 * @date 2020/7/2 16:08
 */
public abstract class AbstractRule implements Rule,Cloneable{
    @Override
    public Rule clone() {
        try {
            return (Rule) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
