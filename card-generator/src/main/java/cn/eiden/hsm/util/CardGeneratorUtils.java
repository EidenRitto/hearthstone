package cn.eiden.hsm.util;

import cn.eiden.hsm.dbdata.CardInfo;
import cn.eiden.hsm.dbdata.Entity;
import cn.eiden.hsm.dbdata.Tag;
import cn.eiden.hsm.enums.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Card生成器
 * @author Eiden J.P Zhou
 * @date 2020/4/10 11:55
 */
@Slf4j
public class CardGeneratorUtils {
    public static final String PACKAGE_PATH = "\\src\\main\\java\\";
    public static Set<String> classNameSet = new HashSet<>();
    public static CardInfo loadInCache(Entity entity){
        //缓存
        CardInfo cardCache = new CardInfo();

        cardCache.setId(entity.getId());
        cardCache.setCardId(entity.getCardId());
        List<Tag> tags = entity.getTag();
        List<Integer> collect = tags.stream().map(Tag::getEnumId).filter(Objects::nonNull).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        cardCache.setTagList(collect);
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
                case CARDRACE:
                    final int cardRaceValue = Integer.parseInt(tag.getValue());
                    Race race = Objects.requireNonNull(EnumUtils.getEnumObject(
                            Race.class, e -> e.getCode() == cardRaceValue)).orElse(null);
                    cardCache.setRace(race);
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
                case DIVINE_SHIELD:
                    cardCache.setDivineShield(Integer.parseInt(tag.getValue()));
                    break;
                case DEATHRATTLE:
                    cardCache.setDeathRattle(Integer.parseInt(tag.getValue()));
                    break;
                case BATTLECRY:
                    cardCache.setBattleCry(Integer.parseInt(tag.getValue()));
                    break;
                case SPELLPOWER:
                    cardCache.setSpellPower(Integer.parseInt(tag.getValue()));
                    break;
                case AURA:
                    cardCache.setAura(Integer.parseInt(tag.getValue()));
                    break;
                case SECRET:
                    cardCache.setSecret(Integer.parseInt(tag.getValue()));
                    break;
                case HERO_POWER:
                    cardCache.setHeroPower(Integer.parseInt(tag.getValue()));
                    break;
                case POISONOUS:
                    cardCache.setPoisonous(Integer.parseInt(tag.getValue()));
                    break;
                case COMBO:
                    cardCache.setCombo(Integer.parseInt(tag.getValue()));
                    break;
                case WINDFURY:
                    cardCache.setWindFury(Integer.parseInt(tag.getValue()));
                    break;
                case OVERLOAD:
                    final int overload = Integer.parseInt(tag.getValue());
                    cardCache.setOverload(overload);
                    break;
                case LIFESTEAL:
                    cardCache.setLifeSteal(Integer.parseInt(tag.getValue()));
                    break;
                case CHOOSE_ONE:
                    cardCache.setChooseOne(Integer.parseInt(tag.getValue()));
                    break;
                case QUEST:
                    cardCache.setQuest(Integer.parseInt(tag.getValue()));
                    break;
                case QUEST_PROGRESS_TOTAL:
                    cardCache.setQuestProgressTotal(Integer.parseInt(tag.getValue()));
                    break;
                case QUEST_REWARD_DATABASE_ID:
                    cardCache.setQuestRewardDatabaseId(Integer.parseInt(tag.getValue()));
                    break;
                case TWINSPELL:
                    cardCache.setTwinSpell(Integer.parseInt(tag.getValue()));
                    break;
                case TWINSPELL_COPY:
                    cardCache.setTwinSpellCopy(Integer.parseInt(tag.getValue()));
                    break;
                case REBORN:
                    cardCache.setReborn(Integer.parseInt(tag.getValue()));
                    break;
                case OUTCAST:
                    cardCache.setOutcast(Integer.parseInt(tag.getValue()));
                    break;
                case SPELLBURST:
                    cardCache.setSpellBurst(Integer.parseInt(tag.getValue()));
                    break;
                case MULTI_CLASS_GROUP:
                    final int intVal = Integer.parseInt(tag.getValue());
                    MultiClassGroup multiClassGroup = Objects.requireNonNull(EnumUtils.getEnumObject(
                            MultiClassGroup.class, e -> e.getCode() == intVal)).orElse(null);
                    cardCache.setMultiClassGroup(multiClassGroup);
                    break;
                default:
                    log.debug(gameTag.name()+" 标签无法识别，enumId="+enumId);
                    break;
            }
        }
        return cardCache;
    }
}
