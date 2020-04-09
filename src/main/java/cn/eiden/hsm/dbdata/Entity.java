package cn.eiden.hsm.dbdata;

import lombok.Data;

import java.util.List;

/**
 * @author 周晋平
 * @date 2020/4/8 10:02
 */
@Data
public class Entity {
    private String cardId;
    private String id;
    private String version;
    private List<Tag> tag;
}
