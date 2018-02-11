package com.game.logic.error.domain;


import com.game.logic.error.anotation.ErrorCodeComment;

/**
 * 错误代码
 * @author liguorui
 * @date 2014年7月2日 下午8:47:19
 */
public interface IErrorCode {

	@ErrorCodeComment(value = "操作成功")
	int	COMMON_SUCC												= 0;

	/***************** 通用 开始 *****************/
	@ErrorCodeComment(value = "数目出错")
	int	COMMON_NUMBER_ERROR										= 10000;
	@ErrorCodeComment(value = "你的帐号在其它地方登录了")
	int	YOU_LOGIN_IN_SOMEWHERE									= 10001;
	/***************** 通用 结束 *****************/

	/***************** 玩家 开始 *****************/
	@ErrorCodeComment(value = "没有足够的金币")
	int	PLAYER_NOT_ENOUGH_GOLD									= 11001;
	@ErrorCodeComment(value = "需要战队等级{0}级,完成每日任务可以快速提升战队等级。")
	int	PLAYER_LEVEL_TOO_LOWER									= 11002;
	@ErrorCodeComment(value = "玩家体力不足")
	int	PLAYER_NOT_ENOUGH_POWER									= 11003;
	@ErrorCodeComment(value = "玩家金币快达到上限了")
	int	PLAYER_NOT_ENOUGH_SPACE_FOR_GOLD						= 11004;
	@ErrorCodeComment(value = "玩家钻石不够")
	int	PLAYER_NOT_ENOUGH_DIAMOND								= 11005;
	@ErrorCodeComment(value = "玩家资源不够")
	int	PLAYER_NOT_ENOUGH_RESOURCE								= 11006;
	@ErrorCodeComment(value = "不存在那个形象")
	int	PLAYER_NO_SUCH_AVATAR									= 11007;
	@ErrorCodeComment(value = "该玩家已下线")
	int	PLAYER_OFF_LINE											= 11008;
	@ErrorCodeComment(value = "您还没登陆")
	int	PLAYER_YOU_HAVE_NOT_LOGIN								= 11009;
	@ErrorCodeComment(value = "充值失败")
	int	PLAYER_RECHARGE_FAIL									= 11010;

	/***************** 玩家 结束 *****************/

	/***************** 机甲 开始 *****************/
	@ErrorCodeComment(value = "机甲已经到最大等级")
	int	MECHA_HAS_MAX_LEVEL										= 12001;
	@ErrorCodeComment(value = "机甲经验不足")
	int	MECHA_NOT_ENOUGH_EXP									= 12002;
	@ErrorCodeComment(value = "机甲品质太低")
	int	MECHA_QUALITY_TOO_LOWER									= 12003;
	@ErrorCodeComment(value = "找不到这个机甲")
	int	MECHA_NO_SUCH_MECHA										= 12004;
	@ErrorCodeComment(value = "机甲等级太低")
	int	MECHA_LEVEL_TOO_LOWER									= 12005;
	@ErrorCodeComment(value = "机甲已经达到最大阶次")
	int	MECHA_ALREADY_IN_MAX_PHASE								= 12006;
	@ErrorCodeComment(value = "机甲已经达到最大品质")
	int	MECHA_HAS_MAX_QUALITY									= 12007;
	@ErrorCodeComment(value = "机甲阶次太低")
	int	MECHA_PHASE_TOO_LOWER									= 12008;
	@ErrorCodeComment(value = "上场机甲超过数目限制")
	int	MECHA_OVER_THE_MAX_NUM_ON_FIELD							= 12009;
	@ErrorCodeComment(value = "不存在这个机甲组")
	int	MECHA_NO_SUCH_MECHA_GROUP								= 12010;
	@ErrorCodeComment(value = "一个机甲不能在同个组中出现两次")
	int	MECHA_CAN_NOT_APPEAR_TWICE_IN_SAME_GROUP				= 12011;
	@ErrorCodeComment(value = "请选择进阶碎片")
	int	MECHA_PLEASE_CHOOSE_UPGRADE_PHASE_CHIP					= 12012;
	/***************** 机甲 结束 *****************/

	/***************** 物品 开始 *****************/
	@ErrorCodeComment(value = "找不到这个物品")
	int	ITEM_NO_SUCH_ITEM										= 13001;
	@ErrorCodeComment(value = "没有足够的材料")
	int	ITEM_NOT_ENOUGH_METERIAL								= 13002;
	@ErrorCodeComment(value = "没有足够的碎片")
	int	ITEM_NOT_ENOUGH_CHIP									= 13003;
	@ErrorCodeComment(value = "材料出错")
	int	ITEM_ERROR_MATERIALS									= 13004;
	@ErrorCodeComment(value = "装备已经满级")
	int	ITEM_EQUIP_HAS_MAX_LEVEL								= 13005;
	@ErrorCodeComment(value = "扫荡券不够")
	int	ITEM_NOT_ENOUGH_WIPE_TICKET								= 13006;
	@ErrorCodeComment(value = "物品数量非法")
	int	ITEM_NUM_UNVALID										= 13007;
	@ErrorCodeComment(value = "不是瓦斯")
	int	ITEM_NOT_A_GAS											= 13008;
	@ErrorCodeComment(value = "不是装备")
	int	ITEM_NOT_A_EQUIPMENT									= 13009;
	@ErrorCodeComment(value = "不是这个机甲的装备")
	int	ITEM_NOT_THIS_MECHA_EQUIPMENT							= 13010;
	@ErrorCodeComment(value = "一次性卖出太多，金币快满了")
	int	ITEM_SELL_TOO_MANY										= 13011;
	@ErrorCodeComment(value = "装备已经到达阶次上限")
	int	ITEM_EQUIP_HAS_MAX_PHASE								= 13012;
	@ErrorCodeComment(value = "没有这个精炼材料")
	int	ITEM_NO_SUCH_REFINE_MATERIAL							= 13013;
	@ErrorCodeComment(value = "没有足够的精炼材料")
	int	ITEM_NOT_ENOUGH_REFINE_MATERIAL							= 13014;
	@ErrorCodeComment(value = "只有背包中的物品能作为精炼材料")
	int	ITEM_ONLY_ITEM_IN_BAG_CAN_BE_A_MATERIAL					= 13015;
	@ErrorCodeComment(value = "精炼物品和精炼材料应该是不同物品")
	int	ITEM_REFINE_ITEM_AND_MATERIAL_SHOULD_BE_DIFF			= 13016;
	@ErrorCodeComment(value = "精炼物品和精炼材料应该是相同品质")
	int	ITEM_REFINE_ITEM_AND_MATERIAL_SHOULD_HAS_SAME_QUALITY	= 13017;
	@ErrorCodeComment(value = "洗练锁定属性行错误")
	int	ITEM_SUCCINCT_LOCK_INDEX_ERROR							= 13018;
	@ErrorCodeComment(value = "锁定数目出错")
	int	ITEM_SUCCINCT_LOCK_NUM_ERROR							= 13019;
	@ErrorCodeComment(value = "该物品不能洗练")
	int	ITEM_CAN_NOT_SUCCINCT									= 13020;
	@ErrorCodeComment(value = "只有装备才能作为精炼材料")
	int	ITEM_ONLY_EQUIPMENGT_CAN_BE_REFINE_MATERIAL				= 13021;
	@ErrorCodeComment(value = "物品不够")
	int	ITEM_NOT_ENOUGH_ITEM									= 13022;
	/***************** 物品 结束 *****************/

	/***************** 副本 开始 *****************/
	@ErrorCodeComment(value = "不存在这个副本")
	int	MISSION_NO_SUCH_MISSION									= 14001;
	@ErrorCodeComment(value = "已经用完副本进入次数")
	int	MISSION_ENTER_TIME_HAS_USED_OUT							= 14002;
	@ErrorCodeComment(value = "不在副本中")
	int	MISSION_NOT_IN_MISSION									= 14003;
	@ErrorCodeComment(value = "副本数据错误")
	int	MISSION_DATA_ERROR										= 14004;
	@ErrorCodeComment(value = "副本还没满星")
	int	MISSION_RANK_HASNOT_MAX									= 14005;
	@ErrorCodeComment(value = "这个副本不能扫荡")
	int	MISSION_THIS_MISSION_CAN_NOT_WIPE						= 14006;
	@ErrorCodeComment(value = "扫荡次数必须大于0")
	int	MISSION_WIPE_NUM_SHOULD_BE_POSITIVE						= 14007;
	@ErrorCodeComment(value = "你还没开放这个副本")
	int	MISSION_YOU_HAVE_NOT_OPEN_THIS_MISSION					= 14008;
	@ErrorCodeComment(value = "你已打过这个副本")
	int	MISSION_YOU_HAVE_PASSED_THIS_MISSION					= 14009;
	@ErrorCodeComment(value = "今日已没有重置次数")
	int	MISSION_YOU_CANT_REVERT_THIS_MISSION					= 14010;
	@ErrorCodeComment(value = "没有这个宝箱")
	int	MISSION_NO_SUCH_BOX										= 14011;
	@ErrorCodeComment(value = "你已经领取过这个宝箱")
	int	MISSION_YOU_HAVE_REWARD_THIS_BOX						= 14012;
	@ErrorCodeComment(value = "星数不够")
	int	MISSION_NO_ENOUGH_RANK									= 14013;
	@ErrorCodeComment(value = "您今天购买副本的次数达到上限，请提升VIP等级购买")
	int	TODAY_BUY_LIMIT											= 14014;
	@ErrorCodeComment(value = "您刚刚打过这个副本，休息一下再来打哦（*^_^*）")
	int	MISSION_CDING											= 14015;
	/***************** 副本 结束 *****************/

	/***************** 活动 开始 *****************/
	@ErrorCodeComment(value = "你已经领取过这个奖励")
	int	ACTIVITY_HAVE_GOT_AWARD									= 15001;
	@ErrorCodeComment(value = "你还没有完成这个活动")
	int	ACTIVITY_HAVE_NO_FINISH									= 15002;
	@ErrorCodeComment(value = "不存在这个活动")
	int	ACTIVITY_NO_SUCH_ACTIVITY								= 15003;
	/***************** 活动 结束 *****************/

	/***************** 竞技场 开始 *****************/
	@ErrorCodeComment(value = "不能挑战你自己")
	int	ARENA_CAN_NOT_CHALLENGE_YOURSELF						= 16001;
	@ErrorCodeComment(value = "挑战CD还没冷却")
	int	ARENA_CHALLENGE_CD_NOT_COOL_DOWN						= 16002;
	@ErrorCodeComment(value = "剩余挑战次数不足")
	int	ARENA_NO_ENOUGH_LEFT_TIME								= 16003;
	@ErrorCodeComment(value = "此挑战者不在你的挑战列表中")
	int	ARENA_CHALLENGE_NOT_IN_YOUR_CHALLENGE_LIST				= 16004;
	@ErrorCodeComment(value = "竞技场挑战次数已经超过购买上限")
	int	ARENA_BUY_NUM_OVER_LIMIT								= 16005;
	@ErrorCodeComment(value = "竞技场挑战次数已经达到上限")
	int	ARENA_LEFT_NUM_HAVE_MAX									= 16006;
	@ErrorCodeComment(value = "挑战CD已经为0")
	int	ARENA_NO_NEED_TO_CLEAR_CHALLENGE_CD						= 16007;
	@ErrorCodeComment(value = "已经用完刷新竞技场商店的次数")
	int	ARENA_USE_OUT_REFERESH_SHOP_NUM							= 16008;
	@ErrorCodeComment(value = "还没到时间领取奖励")
	int	ARENA_NOT_THE_TIME_TO_GET_REWARD						= 16009;
	/***************** 竞技场 开始 *****************/

	/***************** 商店 开始 *****************/
	@ErrorCodeComment(value = "没这个商店")
	int	SHOP_NO_SUCH_SHOP										= 17001;
	@ErrorCodeComment(value = "商店中查无此物")
	int	SHOP_NO_SUCH_ITEM										= 17002;
	@ErrorCodeComment(value = "商店物品已经变化了")
	int	SHOP_ITEM_CHANGED										= 17003;
	@ErrorCodeComment(value = "已售罄")
	int	SHOP_ITEM_HAVE_BOUGHT_OUT								= 17004;
	@ErrorCodeComment(value = "已经用完该商店的刷新次数")
	int	SHOP_HAS_USE_OUT_REFRESH_NUM							= 17005;
	/***************** 商店 结束 *****************/

	/***************** 联办补给站 开始 *****************/
	@ErrorCodeComment(value = "已经用完联办补给站刷新次数")
	int	DEPOT_HAVE_REACH_REFRESH_MAX_NUM						= 18001;
	@ErrorCodeComment(value = "已经用完玛萨拉星刷新次数")
	int	MSLX_HAVE_REACH_REFRESH_MAX_NUM							= 18002;
	/***************** 联办补给站 结束 *****************/

	/***************** 计数器 开始 *****************/
	@ErrorCodeComment(value = "不存在这个计数器")
	int	COUNT_NOT_EXIST											= 19001;
	/***************** 计数器 结束 *****************/

	/***************** 购买资源 开始 *****************/
	@ErrorCodeComment(value = "已经用完购买次数")
	int	BUY_RESOURCE_HAS_USE_OUT_COUNTS							= 20001;
	@ErrorCodeComment(value = "已经到购买上限")
	int	BUY_RESOURCE_HAS_MAX_COUNTS								= 20002;
	/***************** 购买资源 结束 *****************/

	/***************** 消息 开始 *****************/
	@ErrorCodeComment(value = "沒有这个频道")
	int	CHAT_NO_SUCH_CHANNEL									= 21001;
	/***************** 消息 結束 *****************/

	/***************** 战斗 开始 *****************/
	@ErrorCodeComment(value = "您不在战斗场景中")
	int	FIGHT_YOU_ARE_NOT_IN_FIGHT_SCENE						= 22001;
	@ErrorCodeComment(value = "找不到这个施法者")
	int	FIGHT_CAN_NOT_FOUND_SUCH_CASTER							= 22002;
	/***************** 战斗 结束 *****************/

	/***************** 邮件 开始 *****************/
	@ErrorCodeComment(value = "找不到这个邮件")
	int	MAIL_NO_SUCH_MAIL										= 23001;
	@ErrorCodeComment(value = "已经领取过这个邮件的附件")
	int	MAIL_HAS_RECV_ATTACHMENT								= 23002;
	@ErrorCodeComment(value = "这个邮件没有附件")
	int	MAIL_HAVE_NO_ATTACHMENT									= 23003;
	@ErrorCodeComment(value = "没有找到附件对应的奖励")
	int	MAIL_ATTACHMENT_NO_SUCH_REWARD							= 23004;
	/***************** 邮件 结束 *****************/

	/***************** 签到 结束 *****************/
	@ErrorCodeComment(value = "没有更多的签到信息")
	int	SIGN_NO_MORE_SIGN										= 24001;
	@ErrorCodeComment(value = "你已经领过这个签到了")
	int	SIGN_YOU_HAVE_GOT										= 24002;
	/***************** 签到 结束 *****************/

	/***************** 队伍 开始 *****************/
	@ErrorCodeComment(value = "你已经创建了队伍")
	int	TEAM_YOU_HAVE_CREATE_A_TEAM								= 25001;
	@ErrorCodeComment(value = "你已经在队伍中")
	int	TEAM_YOU_HAVE_IN_A_TEAM									= 25002;
	@ErrorCodeComment(value = "没有这个队伍")
	int	TEAM_NO_SUCH_TEAM										= 25003;
	@ErrorCodeComment(value = "队伍已经满了")
	int	TEAM_HAS_MAX_MEMBER										= 25004;
	@ErrorCodeComment(value = "队伍已经开始副本")
	int	TEAM_HAS_START											= 25005;
	@ErrorCodeComment(value = "找不到其它可以加入的队伍")
	int	TEAM_CAN_NOT_FOUND_ANY_TEAM								= 25006;
	@ErrorCodeComment(value = "你没在队伍中")
	int	TEAM_YOU_HAVE_NOT_IN_A_TEAM								= 25007;
	@ErrorCodeComment(value = "你已经准备好了")
	int	TEAM_YOU_HAVE_PREPARED									= 25008;
	@ErrorCodeComment(value = "他已经不在队伍中了")
	int	TEAM_HE_IS_NOT_IN_TEAM_YET								= 25009;
	@ErrorCodeComment(value = "你不是队长")
	int	TEAM_YOU_ARE_NOT_A_CAPTAIN								= 25010;
	@ErrorCodeComment(value = "加入队伍失败")
	int	TEAM_ADD_MEMBER_FAILD									= 25011;
	@ErrorCodeComment(value = "这一波的机甲已经设置满")
	int	TEAM_MECHA_HAS_MAX_THIS_WAVE							= 25012;
	@ErrorCodeComment(value = "预设置机甲出现未知错误")
	int	TEAM_PRE_SET_UNKNOW_ERROR								= 25013;
	@ErrorCodeComment(value = "这个位置只有你的队友才能进行设置")
	int	TEAM_ONLY_YOUR_TEAMMATE_CAN_SET_HERE					= 25014;
	@ErrorCodeComment(value = "这一波已经有人设置了这个机甲")
	int	TEAM_THIS_MECHA_HAS_USED_IN_THIS_WAVE					= 25015;
	@ErrorCodeComment(value = "你已经在其它波次设置了这个机甲")
	int	TEAM_YOU_HAVE_USE_IN_THIS_TEAM							= 25016;
	@ErrorCodeComment(value = "你还没有进行预设置")
	int	TEAM_YOU_SHOULD_PRE_SET_FIRST							= 25017;
	@ErrorCodeComment(value = "这个位置已有机甲")
	int	TEAM_THIS_POS_HAS_PLAYER_MECHA							= 25018;
	@ErrorCodeComment(value = "奖励游戏已经结束")
	int	TEAM_AWARD_GAME_HAS_END									= 25019;
	@ErrorCodeComment(value = "当前不在领奖游戏中")
	int	TEAM_NOT_IN_AWARD_GAMEING								= 25020;
	@ErrorCodeComment(value = "领奖游戏出现未知错误")
	int	TEAM_AWARD_GAMEING_UNKNOW_ERROR							= 25021;
	@ErrorCodeComment(value = "你已经用完组队副本次数")
	int	TEAM_YOU_HAS_USE_OUT_TIMES								= 25022;
	@ErrorCodeComment(value = "该队伍没有公开")
	int	TEAM_NOT_PUBLIC											= 25023;
	@ErrorCodeComment(value = "您只能作为协助者加入公开的队伍")
	int	YOU_CAN_ONLY_JOIN_PUBLIC_AS_ASSIST						= 25024;
	@ErrorCodeComment(value = "协助次数不够")
	int	TEAM_ASSIST_COUNTS_NOT_ENOUGH							= 25025;
	@ErrorCodeComment(value = "本次协助奖励已经领取")
	int	TEAM_ASSIST_AWARD_HAS_GOT								= 25026;
	@ErrorCodeComment(value = "没有这个奖励")
	int	TEAM_ASSIST_AWARD_NO_SUCH_REWARD						= 25027;
	@ErrorCodeComment(value = "组队战斗异常")
	int	TEAM_UNKNOW_EXCEPTION									= 25028;
	/***************** 队伍 结束 *****************/

	/*************** 公会开始 ************************/
	@ErrorCodeComment(value = "您已经有公会了，不能创建公会")
	int	ALREAADY_HAVE_SOCIETY									= 24025;
	@ErrorCodeComment(value = "等级不够无法创建或加入公会")
	int	CONDITION_CREATE_SOCIETY_LEVEL							= 24026;
	@ErrorCodeComment(value = "您已经有公会了，不能加入公会")
	int	ALREAADY_JOIN_SOCIETY									= 24027;
	@ErrorCodeComment(value = "加入的公会不存在")
	int	NOT_EXIST_SOCIETY										= 24028;
	@ErrorCodeComment(value = "您今天已经进行过公会签到了")
	int	ALREADY_QIANDAO_SOCIETY									= 24029;
	@ErrorCodeComment(value = "退出公会后在18小时内无法再申请加入其他公会")
	int	AGAIN_JOIN_SOCIETY_TIME									= 24030;
	@ErrorCodeComment(value = "您申请加入的公会已满人,无法加入")
	int	IS_MAX_SOCIETY_MEMEBERS									= 24031;
	@ErrorCodeComment(value = "只有会长才有踢人权限")
	int	KICK_CONDITION											= 24032;
	@ErrorCodeComment(value = "不能踢会长")
	int	NOT_KICK_SOCIETIER										= 24033;
	@ErrorCodeComment(value = "只有会长才能更改公会信息")
	int	UPDATE_SOCIETY_DATA										= 24034;
	@ErrorCodeComment(value = "您申请的公会数量已达上限")
	int	CAN_APPLY_JOIN_LIMIT									= 24035;
	@ErrorCodeComment(value = "只有会长才能批准或拒绝会员加入公会")
	int	SOCIETIER_CAN_APPLY_JOIN								= 24036;
	@ErrorCodeComment(value = "公会名称重复，不能创建公会")
	int	EXIST_SOCIETY_NAME										= 24037;
	@ErrorCodeComment(value = "查找的公会不存在")
	int	NOT_EXIST_SEARCH_SOCIETY								= 24038;
	@ErrorCodeComment(value = "公会公告长度或入会需求长度太长")
	int	NOTICE_OR_DEMAND_TOO_LONG								= 24039;
	@ErrorCodeComment(value = "您的公会没有玩家派遣雇佣兵")
	int	NOT_SOCIETY_MERCENARY									= 24040;
	@ErrorCodeComment(value = "该公会申请人数已达上限")
	int	MAX_APPLY_SOCIETY_MEMEBERS								= 24041;
	@ErrorCodeComment(value = "没有成员申请加入公会")
	int	NOT_SOCIETY_MEMBERS										= 24042;
	/*************** 公会结束 ************************/

	/***************** 新手引导开始 *****************/
	@ErrorCodeComment(value = "这个引导没有完成")
	int	YOU_NOT_FINISH_THIS_GUIDE								= 30000;
	@ErrorCodeComment(value = "引导失败")
	int	PLAYER_GUIDE_FAILE										= 30001;
	@ErrorCodeComment(value = "新手引导处理器没有注册成功")
	int	PLAYER_GUIDE_NOTIFY_ISNULL								= 30002;
	@ErrorCodeComment(value = "新手触发引导事件失败")
	int	PLAYER_GUIDE_EVENT_FAILE								= 30003;
	@ErrorCodeComment(value = "新手触发引导事件处理器注册失败")
	int	PLAYER_GUIDE_EVENT_ISNULL								= 30004;

	/***************** 新手引导结束 *****************/

	/***************** 抽奖开始 *****************/
	@ErrorCodeComment(value = "抽奖失败")
	int	LOTTERY_FAILE											= 40000;
	@ErrorCodeComment(value = "这个抽奖没有配表")
	int	NOT_CONFIG_TABLE										= 40001;
	@ErrorCodeComment(value = "您的VIP等级不够10级")
	int	LOTTERY_VIP_LEVEL_NOT_ENOUGH							= 40002;
	@ErrorCodeComment(value = "翻牌次数不够")
	int	NOT_ENOUGH_TURN_CARN_COUNT								= 40003;
	/***************** 抽奖结束 *****************/

	/***************** 玛萨拉星开始 *****************/
	@ErrorCodeComment(value = "机甲已阵亡")
	int	MSLX_MECHAS_IS_DEAD										= 500001;
	/***************** 玛萨拉星结束 *****************/

	/***************** 小行星带猎场开始 *****************/
	@ErrorCodeComment(value = "每天0点至10点无法抢夺其他玩家")
	int	NOT_IN_LOOT_TIME										= 24100;
	@ErrorCodeComment(value = "对方玩家身上不满两个该碎片")
	int	NOT_ENOUGH_LOOT_ITEM									= 24101;
	@ErrorCodeComment(value = "该玩家正在被其他中抢夺中，请稍后再抢夺")
	int	ING_HUNT												= 24102;
	@ErrorCodeComment(value = "能量石不够")
	int	NOT_HUNT_ENERGYNUM										= 24103;
	@ErrorCodeComment(value = "该玩家正处于受保护状态中，无法抢夺")
	int	INT_PRODUCTING											= 24104;
	@ErrorCodeComment(value = "没有抢夺记录")
	int	NOT_HUNT_GROUND_LOOT_RECORD								= 24105;
	@ErrorCodeComment(value = "您今天能量石购买次数已达到上限")
	int	MAX_BUY_TIMES											= 24106;
	@ErrorCodeComment(value = "您拥有的能量石已经达到上限了")
	int	HAVE_MAX_NUMS											= 24107;
	@ErrorCodeComment(value = "玩家处理系统保护中，无法对他进行复仇")
	int	NOT_IN_LOOT_REVENGE_TIME								= 24108;
	@ErrorCodeComment(value = "玩家身上的电磁脉冲击不够")
	int	NOT_PRODUCT_ITEM										= 24109;
	@ErrorCodeComment(value = "对方处于系统保护中，不能请公会复仇，但是可以亲自复仇")
	int	NOT_REVEGE_SOCIETY_BUT_OWN								= 24110;
	@ErrorCodeComment(value = "今天协助会员复仇次数已经用完")
	int	TODAY_REVEGE_SOCIETY_LIMIT								= 24111;
	@ErrorCodeComment(value = "玩家和你不是同一公会，无法帮助复仇")
	int	NOT_SAME_SOCIETY										= 24112;
	@ErrorCodeComment(value = "对方和你处于同一公会，无法对其复仇")
	int	IN_SAME_SOCIETY											= 24113;
	@ErrorCodeComment(value = "对方和你处于同一公会，无法对其抢夺")
	int	IN_SAME_SOCIETY_NOT_LOOT								= 24114;
	@ErrorCodeComment(value = "你协助复仇的玩家和你不在同一个公会，不能帮其复仇")
	int	IN_NOT_SAME_SOCIETY_NOT_REVEBGE							= 24115;
	@ErrorCodeComment(value = "当前处于冷却时间内，不能发布公会复仇")
	int	IN_LENGEQUE_TIME										= 24116;
	@ErrorCodeComment(value = "已有其他公会成员成功帮助复仇了")
	int	HAVE_OTHER_REVENGE										= 24117;
	@ErrorCodeComment(value = "玩家还没开放小行星功能不能协助复仇")
	int	NOT_OPEN_HUNTGROUND										= 24118;
	@ErrorCodeComment(value = "公会复仇不能自己给自己复仇")
	int	NOT_CAN_MYSELF_REVENGE									= 24119;
	@ErrorCodeComment(value = "已经复仇了")
	int	ALERADY_REVENGE											= 24120;
	@ErrorCodeComment(value = "当前玩家不存在")
	int	Not_LOOK_HUNTGROUND_PLAYER								= 24121;
	@ErrorCodeComment(value = "当前玩家等级不满足小行星开放条件")
	int	PLAYER_HUNTGROUND_NOT_ENGOUT_LEVEL						= 24122;
	/***************** 小行星带猎场结束 *****************/

	/***************** 奖励活动开始 *****************/
	@ErrorCodeComment(value = "您的新手签到已全部完成")
	int	ALL_QIANDAO												= 24201;
	@ErrorCodeComment(value = "您今天已签到")
	int	ALREAY_QIANDAO											= 24202;
	@ErrorCodeComment(value = "您的VIP等级不够，请提升VIP等级")
	int	VIP_LEVEL_NOT											= 24203;
	@ErrorCodeComment(value = "您已经购买该VIP礼包,不能再购买")
	int	ALEREAY_BUY_VIP_LEVEL									= 24204;
	@ErrorCodeComment(value = "您已经领取了消费送礼奖励，无法重复领取")
	int	ALEREAY_RECEIVE_REWARD									= 24205;
	@ErrorCodeComment(value = "您已经购买了成长基金，无法重复购买")
	int	ALEREAY_BUY_GROW_JJ										= 24206;
	@ErrorCodeComment(value = "您还没有购买了成长基金，无法领取奖励")
	int	NOT_BUY_GROW_JJ											= 24207;
	@ErrorCodeComment(value = "您的等级不够，无法领取奖励")
	int	LEVEL_NOT												= 24208;
	@ErrorCodeComment(value = "购买VIP礼包失败")
	int	BUY_LIBAO_FAIL											= 24209;
	@ErrorCodeComment(value = "月卡只能再一个月购买一次")
	int	ONLY_BUY_ONE_MONTHCARD									= 24210;
	/***************** 奖励活动结束 *****************/

}