# Java - 炉石传说
## 这是什么
这是用Java语言实现的游戏--炉石传说
- 实现了什么功能
  - [x] 炉石传说
    - [x] 主要组件
      - [x] 随从
      - [x] 英雄
      - [x] 武器
      - [x] 奥秘
      - [x] 任务
    - [x] 卡牌机制
      - [x] 随从特效
      - [x] 战吼
      - [x] 亡语
      - [ ] <a href="#otherCard">其他卡牌效果</a>
    - [x] 卡牌
      - [ ] 基础包
      - [ ] 经典包
      - [ ] 荣誉室
      - [ ] <a href="#otherClass">其他扩展包</a>
    - [ ] AI
    - [x] QQ群对战
  - [ ] 酒馆战棋

- 效果图

![alt 开始](img/开始.png)

![alt 选择套牌](img/选择套牌.png)

![alt 出牌](img/出牌.png)

![alt 战场](img/战场.png)

![alt 战场2](img/战场2.png)

## 包含组件
| 模块             | 介绍        | 功能描述                                      |
| -------------    | -------    | ---------------------                        |
| hearth-core      | 核心模块    | 整个游戏的架构，接口                            |
| hearth-card      | 卡牌模块    | 游戏的卡牌各种具体实现                          |
| hearth-control   | 控制器模块  | 游戏的测试启动和控制器,目前只能以文字的交互形式启动  |
| card-generator   | 卡牌生成器  | 从暴雪的卡牌数据库中生成对应的java类              |

## 快速开始
- 克隆项目
- 编译
  - 使用maven工具 install核心模块【hearth-core】
  - 在【card-generator】模块中运行cn/eiden/hsm/util/XmlUtil.java
  - 使用maven工具 install全部模块【hearth】
- 运行
  - 项目的测试运行全部都在【hearth-control】模块中
  - java控制台运行
    - 运行cn/eiden/hsm/cockpit/console/ConsoleCockpit.java
  - QQ运行
    - 配置cn.eiden.hsm.cockpit.mirai.MiraiApplication中的机器人账号和密码
    - 运行MiraiApplication的main方法
  - 微信运行（未实装）


## 如何扩展添加新的卡牌

### 为什么要添加卡牌

- 炉石传说运营了6年，19+1个卡牌扩展包，卡牌数量太多
- 本项目从卡牌数据源能生成全部的牌面信息（费用，稀有度，职业，攻击力，生命值等静态信息）
- 作者将会从底层实现全部的游戏功能，并且至少每一种效果的卡牌会写出至少一个示例卡牌，其他的卡牌效果需要被仿写
- 希望喜欢炉石传说和Java的朋友和我一起为本项目贡献卡牌

### 有哪些卡牌需要被添加

- 大多数白板随从，武器（无描述的卡牌）无需手动添加
- 只有关键字描述的随从（比如[圣盾] [嘲讽] [冲锋]等）无需手动添加
- 关键字配合文字描述的卡牌（比如 <b>战吼：</b>从你的牌库中抽一张<b>奥秘</b>牌。）
- 全部的法术牌
- 其他牌面带有描述的卡牌

### 如何添加卡牌

> 在【hearth-card】模块中使用搜索功能找到你要实现的卡牌 （例如，我想实现卡牌【阿莱克丝塔萨】）  
> 如果存在多个结果，则选择对应拓展包内的卡牌类（【阿莱克丝塔萨】在经典包，经典包的包名为【expert1】）  
> 复制这个类，在cn/eiden/hsm/game/card目录中找到对应的扩展包和职业（【阿莱克丝塔萨】是经典包的中立随从对应的包名为【classic.neutral】）  
> 在这个包中创建一个新类继承自上一步的类，新的类名一般在前面得类后面加Card  

```java
public class AlexstraszaCard extends Alexstrasza {
}
```

> 如果这张卡牌在打出时需要选择目标（炉石传说中的卡牌只有选择一个目标和不需要选择目标两种）则需要在类头加入注解@TargetScope  
>> 需要注意的是，有些卡牌可以选择全部目标(敌/我)(随从/英雄) 如[火球术][1]  
>> 有些卡牌则只能选择敌方目标，如[横扫][2]  
>> 示例中只能以双方英雄为目标则给注解添加以下属性  
>> 灵活使用TargetScope注解  
```java
@TargetScope(classScope = HeroMinion.class)
public class AlexstraszaCard extends Alexstrasza {
}
```

> 战吼随从重写selfBattleCry方法，返回一个战吼接口的实现  

```java
@TargetScope(classScope = HeroMinion.class)
public class AlexstraszaCard extends Alexstrasza {
    /**
     * "<b>战吼：</b>\n"
     * + "将一方英雄的剩余生命值变为15。"
     */
    @Override
    protected Battle selfBattleCry() {
        return (e,t) -> t.setHealth(15);
    }
}
```

> 完成！一张新的卡牌特效成功实现  
> 其他卡牌则参考项目中已经实现的其他卡牌  


#### 附录

<a name="otherCard"></a>
- [ ] 卡牌关键效果及其部分demo示例（已经实现但没有示例的为自动生成，无需重写）
  - [x] 随从特效 
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/warrior/FrothingBerserkerCard.java">暴乱狂战士</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/brm/neutral/GrimPatronCard.java">恐怖的奴隶主</a>
  - [x] 战吼
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/ungoro/mage/ArcanologistCard.java">秘法学家</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/kara/mage/MedivhSValetCard.java">麦迪文的男仆</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/mage/KirinTorMageCard.java">肯瑞托法师</a>
  - [x] 亡语
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/naxx/MadScientistCard.java">疯狂的科学家</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/base/LeperGnomeCard.java">麻风侏儒</a>
  - [x] 嘲讽
  - [x] 冲锋
  - [x] 法强
  - [x] 光环
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/SouthseaCaptainCard.java">南海船长</a>
  - [x] 风怒
  - [x] 圣盾
  - [x] 潜行
  - [x] 剧毒
  - [x] 沉默
  - [x] 冻结
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/dalaran/mage/RayOfFrostCard.java">霜冻射线</a>
  - [x] 抉择
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/druid/DruidOfTheClawCard.java">利爪德鲁伊</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/druid/WrathCard.java">愤怒</a>
  - [x] 连击
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/rogue/Si7AgentCard.java">军情七处特工</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/classic/rogue/EviscerateCard.java">刺骨</a>
  - [x] 过载
  - [x] 随机卡牌
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/dalaran/neutral/ExoticMountsellerCard.java">特殊坐骑商人</a>
  - [x] 发现
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/uldum/neutral/GoldenScarabCard.java">金甲虫</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/blacktemple/priest/RenewCard.java">复苏</a>
  - [x] 吸血
  - [x] 任务
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/ungoro/rogue/TheCavernsBelowCard.java">探索地下洞穴</a>
  - [ ] 进化
  - [x] 突袭
  - [ ] 招募
  - [ ] 回响
  - [ ] 磁力
  - [ ] 超杀
  - [x] 免疫
  - [x] 复生
  - [x] 双生法术
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/dalaran/mage/RayOfFrostCard.java">霜冻射线</a>
  - [ ] 休眠
  - [x] 流放
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/blacktemple/demonhunter/SkullOfGulDanCard.java">古尔丹之颅</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/demon_hunter_initiate/demonhunter/EyeBeamCard.java">眼棱</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/demon_hunter_initiate/demonhunter/ConsumeMagicCard.java">吞噬魔法</a>
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/blacktemple/demonhunter/CrimsonSigilRunnerCard.java">火色魔印奔行者</a>
  - [x] 法术迸发 
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/sch/priest/CabalAcolyteCard.java">秘教侍僧</a>
  - [x] 交换手牌和牌库
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/sch/priest/MindrenderIlluciaCard.java">裂心者伊露希亚</a>
  - [x] 手牌洗入牌库
  <a href="hearth-card/src/main/java/cn/eiden/hsm/game/card/sch/demonhunter/GlideCard.java">滑翔</a>
  - [ ] 双职业卡牌

<a name="otherClass"></a>
- [ ] 卡牌包
  - [ ] 基础包 CORE
  - [ ] 经典包 CLASSIC
  - [ ] 荣誉室 HOF
  - [ ] 纳克萨玛斯 NAXX
  - [ ] 地精大战侏儒 GVG 
  - [ ] 黑石山的火焰 BRM 
  - [ ] 冠军的试炼 TGT
  - [ ] 探险者协会 LOE
  - [ ] 上古之神的低语 OG
  - [ ] 卡拉赞之夜 KAR
  - [ ] 龙争虎斗加基森 CFM GANGS
  - [ ] 勇闯安戈洛 UNG 
  - [ ] 冰封王座的骑士 ICC
  - [ ] 狗头人与地下世界 LOOT
  - [ ] 女巫森林 GIL
  - [ ] 砰砰计划 BOT
  - [ ] 拉斯塔哈的大乱斗 TRL
  - [ ] 暗影崛起 DAL
  - [ ] 奥丹姆奇兵 ULD
  - [ ] 巨龙降临 DRG
  - [ ] 迦拉克隆的觉醒 YOD
  - [ ] 外域的灰烬 BT
  - [x] 通灵学院 SCH
  
### 交流群 ：649207014
  
  
[1]: hearth-card/src/main/java/cn/eiden/hsm/game/card/base/mage/FireballCard.java
[2]: hearth-card/src/main/java/cn/eiden/hsm/game/card/base/druid/SwipeCard.java