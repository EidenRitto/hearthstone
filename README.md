# eiden - hearthstone
## 这是什么

## 包含组件
| 模块             | 介绍        |
| -------------    | -------    |
| hearth-core      | 核心模块    |
| hearth-card      | 卡牌模块    |
| hearth-control   | 控制器模块  |
| card-generator   | 卡牌生成器  |

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
    - qq模块使用http插件，系统中使用的是酷Q
    - <a href="#coolq">Maven 安装配置酷Q</a>
    - 运行cn/eiden/hsm/cockpit/coolq/HearthApplication.java
  - 微信运行（未实装）
  
<a name="coolq"></a>
#### 1. 下载 [酷Q](https://cqp.cc/)... (如果有 酷Q Pro 的话效果更好哦!)
下载完后解压到你想安装的目录下<br>
首次启动请运行 `cqa.exe` 或 `cqp.exe`, 并登陆机器人的 QQ 号<br>
然后退出 酷Q (右键悬浮窗点退出)<br>

#### 2. 添加 [酷Q HTTP 插件](https://cqp.cc/t/30748):
把 `.cpk` 文件下载下来, 放进 `酷Q安装目录\app` 文件夹里<br>
启动 酷Q<br>
右键悬浮窗, 然后点击 `应用 -> 应用管理`<br>
列表里现在应该有 `[未启用] HTTP API`, 点击它, 点击启用<br>
启用的时候会提示需要一些敏感权限, 选择继续<br>
启用之后在 `酷Q安装目录\app` 文件夹里会出现 `io.github.richardchien.coolqhttpapi` 文件夹<br>
退出 酷Q<br>

#### 3. 配置 酷Q HTTP 插件:
在 `io.github.richardchien.coolqhttpapi` 文件夹里创建一个文件名为 `config.cfg` 的配置文件<br>
并在其中写入以下代码<br>

```
[general]
host=0.0.0.0
port=接收端口
post_url=http://127.0.0.1:发送端口
enable_backward_compatibility=false
```

把发送端口和接收端口改成你的机器人程序里用的端口 (测试机器人的接收为`31091`, 发送`31092`)<br>
注意: 酷Q 配置里的`发送端口`要和传进 Picq 的`接收端口`一样, 然后 Picq 的`发送端口`也要和 酷Q 的`接收端口`一样!<br>
( 这是因为 酷Q 需要发送到 Picq 的接收端口去, 而不是发送到对方的发送端口ww )<br>
如果 酷Q 要和你的机器人程序分开运行的话, 请把`127.0.0.1`改成你的机器人部署的服务器的地址<br>
保存配置文件<br>

#### 4. 配置完成! 启动 酷Q!




















尚未实装的功能：

1186---TWINSPELL_COPY--双生法术
470---FINISH_ATTACK_SPELL_ON_DAMAGE--攻击后的特效，只有金手指和超级对撞器有
350---ADJACENT_BUFF--相邻的buff
351---FLAVORTEXT
1456---IS_BACON_POOL_MINION
476---MULTIPLE_CLASSES
114---ELITE
994---MARK_OF_EVIL
1333---OUTCAST
1057---SHRINE
997---DECK_RULE_MOD_DECK_SIZE
1452---COLLECTION_RELATED_CARD_DATABASE_ID
1298---UI_BUFF_COST_UP
998---FAST_BATTLECRY
917---HEALTH_DISPLAY
12---PREMIUM
480---MULTI_CLASS_GROUP
1193---TWINSPELL
482---GRIMY_GOONS
1192---SIDEQUEST
240---IMMUNE
362---AURA
483---JADE_LOTUS
1107---HIDE_WATERMARK
363---POISONOUS
484---KABAL
364---HOW_TO_EARN
2---TAG_SCRIPT_DATA_NUM_1
365---HOW_TO_EARN_GOLDEN
3---TAG_SCRIPT_DATA_NUM_2
367---AI_MUST_PLAY
401---EVIL_GLOW
247---CANT_BE_DESTROYED
402---HIDE_STATS
1464---MOVE_MINION_HOVER_TARGET_SLOT
403---INSPIRE
1342---USE_DISCOVER_VISUALS
920---ENABLE_HEALTH_DISPLAY
404---RECEIVES_DOUBLE_SPELLDAMAGE_BONUS
923---OVERKILL
1085---REBORN
370---AFFECTED_BY_SPELL_POWER
251---AttackVisualType
890---DISCARD_CARDS
1118---AFFECTED_BY_HEALING_DOES_DAMAGE
375---MULTIPLY_BUFF_VALUE
1116---PLAYER_TAG_THRESHOLD_VALUE
377---TOPDECK
1115---PLAYER_TAG_THRESHOLD_TAG_ID
1114---NON_KEYWORD_ECHO
535---QUEST_PROGRESS_TOTAL
1199---COIN_MANA_GEM
415---DISCOVER
1517---WATERMARK_OVERRIDE_CARD_SET
32---TRIGGER_VISUAL
1090---DORMANT_VISUAL
1132---ALTERNATE_MOUSE_OVER_CARD
1099---PLAYER_BASE_SHRINE_DECK_ID
380---HERO_POWER
1491---BACON_HERO_CAN_BE_DRAFTED
783---DUNGEON_PASSIVE_BUFF
388---SPARE_PART
389---FORGETFUL
268---DevState
1125---DECK_LIST_SORT_ORDER
785---GHOSTLY
1365---GALAKROND_HERO_CARD
424---RITUAL
1089---QUEST_REWARD_DATABASE_ID
426---APPEAR_FUNCTIONALLY_DEAD
1142---MOUSE_OVER_CARD_APPEARANCE
1263---EMPOWER
791---RUSH
396---HEROPOWER_DAMAGE
793---HERO_DECK_ID
1413---DECK_RULE_COUNT_AS_COPY_OF_CARD_ID
311---CANT_BE_TARGETED_BY_SPELLS
554---DEATH_KNIGHT
1531---START_OF_COMBAT
1135---ENCHANTMENT_BANNER_TEXT
314---CANT_BE_SILENCED
556---BOSS
956---COLLECTIONMANAGER_FILTER_MANA_EVEN
957---COLLECTIONMANAGER_FILTER_MANA_ODD
682---HIDE_HEALTH
441---JADE_GOLEM
683---HIDE_ATTACK
321---COLLECTIBLE
200---CARDRACE
684---HIDE_COST
201---FACTION
443---CHOOSE_ONE
685---LIFESTEAL
1423---BACON_IS_KEL_THUZAD
1421---BACON_MINION_IS_LEVEL_TWO
325---TARGETING_ARROW_TEXT
205---SUMMONED
448---UNTOUCHABLE
846---ECHO
208---FREEZE
968---START_OF_GAME
849---MODULAR
1440---TECH_LEVEL
292---ARMOR
293---MORPH
1282---HEROIC_HERO_POWER
330---ENCHANTMENT_BIRTH_VISUAL
451---SCORE_VALUE_1
1437---BACON_ACTION_CARD
331---ENCHANTMENT_IDLE_VISUAL
332---CANT_BE_TARGETED_BY_HERO_POWERS
453---SCORE_VALUE_2
212---ENRAGED
335---InvisibleDeathrattle
456---CANT_BE_FATIGUED
215---OVERLOAD
457---AUTOATTACK
217---DEATHRATTLE
338---TAG_ONE_TURN_EFFECT
976---ENCHANTMENT_INVISIBLE
218---BATTLECRY
339---SILENCE
219---SECRET
857---IGNORE_HIDE_STATS_FOR_BIG_CARD
979---PUZZLE
1438---GAME_MODE_BUTTON_SLOT
1297---UI_BUFF_ATK_UP
1296---UI_BUFF_COST_DOWN
1295---UI_BUFF_SET_COST_ZERO
1052---GEARS
1294---UI_BUFF_HEALTH_UP
220---COMBO
462---QUEST
342---ARTISTNAME
189---WINDFURY
982---PUZZLE_TYPE
1443---UI_BUFF_DURABILITY_UP
227---CANT_ATTACK
349---ImmuneToSpellpower