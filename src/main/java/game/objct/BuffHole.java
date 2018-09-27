package game.objct;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 场地光环
 * @Modified By:
 */
public class BuffHole extends GameObject{
    private long addHealth;
    private long addAttack;
    private long fixedHealth;
    private long fixedAttack;

    public long getAddHealth() {
        return addHealth;
    }

    public void setAddHealth(long addHealth) {
        this.addHealth = addHealth;
    }

    public long getAddAttack() {
        return addAttack;
    }

    public void setAddAttack(long addAttack) {
        this.addAttack = addAttack;
    }

    public long getFixedHealth() {
        return fixedHealth;
    }

    public void setFixedHealth(long fixedHealth) {
        this.fixedHealth = fixedHealth;
    }

    public long getFixedAttack() {
        return fixedAttack;
    }

    public void setFixedAttack(long fixedAttack) {
        this.fixedAttack = fixedAttack;
    }
}
