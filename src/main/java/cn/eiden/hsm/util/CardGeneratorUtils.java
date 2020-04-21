package cn.eiden.hsm.util;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.dbdata.Tag;
import cn.eiden.hsm.enums.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * Card生成器
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:55
 */
@Slf4j
public class CardGeneratorUtils {
    public static final String PACKAGE_PATH = "\\src\\main\\java\\";
    public static CardInfo loadInCache(Entity entity){
        //缓存
        CardInfo cardCache = new CardInfo();

        cardCache.setId(entity.getId());
        cardCache.setCardId(entity.getCardId());
        List<Tag> tags = entity.getTag();
        for (Tag tag : tags) {
            final int enumId = Integer.parseInt(tag.getEnumId());
            GameTag gameTag = Objects.requireNonNull(
                    EnumUtils.getEnumObject(GameTag.class, e -> e.getCode() == enumId))
                    .orElse(null);
            if (gameTag == null){
                log.error("枚举id"+enumId+"找不到对应的枚举");
                continue;
            }
            switch (gameTag){
                case CARDNAME:
                    cardCache.setCardName(tag.getEnUS());
                    cardCache.setCardCnName(tag.getZhCN());
                    break;
                case CARDTEXT:
                    cardCache.setCardText(tag.getZhCN());
                    break;
                case COST:
                    cardCache.setCost(Integer.parseInt(tag.getValue()));
                    break;
                case CARD_SET:
                    final int cardSetValue = Integer.parseInt(tag.getValue());
                    CardSet cardSet = Objects.requireNonNull(EnumUtils.getEnumObject(
                            CardSet.class, e -> e.getCode() == cardSetValue)).orElse(null);
                    cardCache.setCardSet(cardSet);
                    break;
                case CLASS:
                    final int cardClassValue = Integer.parseInt(tag.getValue());
                    CardClass cardClass = Objects.requireNonNull(EnumUtils.getEnumObject(
                            CardClass.class, e -> e.getCode() == cardClassValue)).orElse(null);
                    cardCache.setCardClass(cardClass);
                    break;
                case CARDTYPE:
                    final int cardTypeValue = Integer.parseInt(tag.getValue());
                    CardType cardType = Objects.requireNonNull(EnumUtils.getEnumObject(
                            CardType.class, e -> e.getCode() == cardTypeValue)).orElse(null);
                    cardCache.setCardType(cardType);
                    break;
                case RARITY:
                    final int cardRarityValue = Integer.parseInt(tag.getValue());
                    Rarity cardRarity = Objects.requireNonNull(EnumUtils.getEnumObject(
                            Rarity.class, e -> e.getCode() == cardRarityValue)).orElse(null);
                    cardCache.setRarity(cardRarity);
                    break;
                case DURABILITY:
                    final int durabilityValue = Integer.parseInt(tag.getValue());
                    cardCache.setDurability(durabilityValue);
                    break;
                case ATK:
                    final int atkValue = Integer.parseInt(tag.getValue());
                    cardCache.setAtk(atkValue);
                    break;
                case HEALTH:
                    final int healthValue = Integer.parseInt(tag.getValue());
                    cardCache.setHealth(healthValue);
                    break;
                case TAUNT:
                    cardCache.setTaunt(Integer.parseInt(tag.getValue()));
                    break;
                case CHARGE:
                    cardCache.setCharge(Integer.parseInt(tag.getValue()));
                    break;
                case STEALTH:
                    cardCache.setStealth(Integer.parseInt(tag.getValue()));
                    break;
                default:
                    log.debug(gameTag.name()+" 标签无法识别，enumId="+enumId);
                    break;
            }
        }
        return cardCache;
    }
}
