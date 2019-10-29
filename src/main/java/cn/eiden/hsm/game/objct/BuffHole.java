package cn.eiden.hsm.game.objct;


import lombok.Data;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 场地光环
 * @Modified By:
 */
@Data
public class BuffHole extends GameObject{
    private long addHealth;
    private long addAttack;
    /**触发条件 prerequisite 可能存在 种族因素*/
    private Ethnicity prerequisite;
    private long fixedHealth;
    private long fixedAttack;

    public BuffHole() {

    }

    public BuffHole(long addAttack, long addHealth, Ethnicity prerequisite) {
        this.addHealth = addHealth;
        this.addAttack = addAttack;
        this.prerequisite = prerequisite;
    }

    public BuffHole(long addHealth, long addAttack, Ethnicity prerequisite, long fixedHealth, long fixedAttack) {
        this.addHealth = addHealth;
        this.addAttack = addAttack;
        this.prerequisite = prerequisite;
        this.fixedHealth = fixedHealth;
        this.fixedAttack = fixedAttack;
    }
}
