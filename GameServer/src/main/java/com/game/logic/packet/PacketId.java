package com.game.logic.packet;

/**
 * 协议id
 * @author liguorui
 * @date 2014年6月27日 上午11:54:38
 */
public interface PacketId {

	public static final int	REQ_CESHI							= 1001;

	public static final int	RESP_CESHI							= 1002;

	public static final int	REQ_PRO_CESHI							= 1003;

	public static final int	RESP_PRO_CESHI							= 1004;

	/** 心跳 */
	public static final int	REQ_HEART_TICK							= 9000;

	/** 请求登录 */
	public static final int	REQ_LOGIN								= 10001;
	/** 返回登录结果 */
	public static final int	RESP_LOGIN								= 10002;
	/** 请求玩家每个模块数据 */
	public static final int	REQ_PLAYER_MODULES_DATA					= 10003;
	/** 返回状态码 */
	public static final int	RESP_ERROR_CODE							= 10004;

	/** 创建玩家角色 */
	public static final int	REQ_PLAYER_CREATE						= 11001;
	/** 返回玩家信息 */
	public static final int	RESP_PLAYER_INFO						= 11002;
	/** 返回玩家属性 */
	public static final int	RESP_PLAYER_PROPERTY					= 11003;
	/** 返回玩家资源 */
	public static final int	RESP_PLAYER_RESOURCE					= 11004;
	/** 修改玩家昵称 */
	public static final int	REQ_PLAYER_CHANGE_NAME					= 11005;
	/** 请求修改玩家形象 */
	public static final int	REQ_PLAYER_CHANGE_AVATAR				= 11006;
	/** 请求设置 */
	public static final int	REQ_PLAYER_SETTINGS						= 11007;
	/** 返回设置 */
	public static final int	RESP_PLAYER_SETTINGS					= 11008;
	/** 生成玩家随机名字 */
	public static final int	REQ_PLAYER_RAND_NAME					= 11009;
	/** 返回玩家随机名字 */
	public static final int	RESP_PLAYER_RAND_NAME					= 11010;
	/** 返回玩家升级信息 */
	public static final int	RESP_PLAYER_LEVEL_UP					= 11011;

	/** 請求其他玩家信息 */
	public static final int	REQ_OTHER_PLAYER_INFO					= 11200;
	/** 返回其他玩家信息 */
	public static final int	RESP_OTHER_PLAYER_INFO					= 11201;

	/** 请求消息 */
	public static final int	REQ_PLAYER_MESSAGES						= 11100;
	/** 返回消息 */
	public static final int	RESP_PLAYER_MESSAGES					= 11101;

	/** 返回机甲信息 */
	public static final int	RESP_PLAYER_MECHAS						= 12001;
	/** 请求机甲升级 */
	public static final int	REQ_PLAYER_MECHA_UPGRADE_LEVEL			= 12002;
	/** 请求机甲升阶 */
	public static final int	REQ_PLAYER_MECHA_UPGRADE_PHASE			= 12003;
	/** 请求机甲升品 */
	public static final int	REQ_PLAYER_MECHA_UPGRADE_QUALITY		= 12004;
	/** 返回机甲升级 */
	public static final int	RESP_PLAYER_MECHA_UPGRADE_LEVEL			= 12005;
	/** 返回机甲升阶 */
	public static final int	RESP_PLAYER_MECHA_UPGRADE_PHASE			= 12006;
	/** 返回机甲升品 */
	public static final int	RESP_PLAYER_MECHA_UPGRADE_QUALITY		= 12007;
	/** 请求机甲穿或脱装备 */
	public static final int	REQ_PLAYER_MECHA_EQUIP					= 12008;
	/** 请求机甲提升技能等级 */
	public static final int	REQ_PLAYER_MECHA_UPGRADE_SKILL_LEVEL	= 12009;
	/** 返回机甲技能信息 */
	public static final int	RESP_PLAYER_MECHA_SKILLS				= 12010;
	/** 请求设置机甲方案 */
	public static final int	REQ_PLAYER_MECHA_SET_GROUP				= 12011;
	/** 返回设置机甲方案 */
	public static final int	RESP_PLAYER_MECHA_GROUPS				= 12012;
	/** 返回战斗后的机甲属性 */
	public static final int	RESP_PLAYER_MECHA_PROP_AFTER_FIGHT		= 12014;
	/** 返回机甲升级 */
	public static final int	RESP_PLAYER_MECHA_LEVEL_UP				= 12015;
	/** 设置装备组 */
	public static final int	REQ_SET_EQUIP_GROUP						= 12016;

	/** 返回装备组信息 */
	public static final int	RESP_PLAYER_EQUIP_GROUP					= 13000;
	/** 返回物品信息 */
	public static final int	RESP_PLAYER_ITEMS						= 13001;
	/** 请求装备升级 */
	public static final int	REQ_PLAYER_ITEM_UPGRADE_LEVEL			= 13002;
	/** 请求出售装备 */
	public static final int	REQ_SELL_ITEM							= 13003;
	/** 请求精炼装备 */
	public static final int	REQ_PLAYER_ITEM_REFINE					= 13004;
	/** 请求装备洗练 */
	public static final int	REQ_PLAYER_ITEM_SUCCINCT				= 13005;
	/** 请求使用物品 */
	public static final int	REQ_PLAYER_USE_ITEM						= 13006;
	/** 请求物品合成 */
	public static final int	REQ_ITEM_MERGE							= 13007;
	/** 返回使用礼包显示得到那些物品 */
	public static final int	RESP_SHOW_USE_GIFT_DATA					= 13008;
	/** 请求升级机甲上的所有装备 */
	public static final int	REQ_UPGRADE_ITEMS_ON_MECHA				= 13009;
	/** 返回升级机甲上的所有装备 */
	public static final int	RESP_UPGRADE_ITEMS_ON_MECHA				= 13010;

	/** 返回玩家副本信息 */
	public static final int	RESP_PLAYER_MISSIONS					= 14001;
	/** 请求进入副本 */
	public static final int	REQ_PLAYER_ENTER_MISSION				= 14002;
	/** 请求副本结束 */
	public static final int	REQ_PLAYER_END_MISSION					= 14003;
	/** 请求扫荡副本 */
	public static final int	REQ_WIPE_OUT_MISSION					= 14004;
	/** 返回扫荡结果 */
	public static final int	RESP_WIPE_OUT_MISSION_RESULT			= 14005;
	/** 返回请求进入副本的结果 */
	public static final int	RESP_PLAYER_ENTER_MISSION				= 14006;
	/** 返回副本结束结果 */
	public static final int	RESP_PLAYER_END_MISSION					= 14007;

	public static final int	REQ_PLAYER_ACTIVATE_MISSION				= 14008;
	/*** 激活副本 （虫群之心，） */
	public static final int	RESP_PLAYER_ACTIVATE_MISSION			= 14009;

	/** 返回播放剧情 */
	public static final int	RESP_MISSION_PLOT_START					= 14025;
	/** 请求播放结束 */
	public static final int	REQ_MISSION_PLOT_FINISH					= 14026;
	/** 返回副本剩余时间 */
	public static final int	RESP_MISSION_TIME_LIMIT					= 14029;
	/** 返回移动完成 */
	public static final int	REQ_MISSION_MOVE_END					= 14031;
	/** 请求副本暂停 */
	public static final int	REQ_MISSION_PAUSE						= 14033;
	/** 请求终止副本 */
	public static final int	REQ_MISSION_ABORT						= 14037;

	/** 购买副本次数 */
	public static final int	REQ_PLAYER_BUY_MISSION					= 14021;
	/** 退出副本 */
	public static final int	REQ_PLAYER_EXIT_MISSION					= 14014;

	/** 请求领取副本宝箱 */
	public static final int	REQ_PLAYER_MISSION_REWARD_BOX			= 14010;

	/** 返回已领取副本宝箱 */
	public static final int	RESP_PLAYER_MISSION_REWARD_BOX			= 14023;
	
	/** 返回每个副本已领取副本宝箱信息 */
	public static final int	RESP_PLAYER_MISSION_REWARD_BOXS			= 14050;
	/** 请求领取副本的奖励宝箱 */
	public static final int	REQ_RECEIVE_MISSION_REWARD_BOXS			= 14051;

	/**** 请求玛萨拉星进入副本 */
	public static final int	REQ_PLAYER_ENTER_MSLX_MISSION			= 14011;
	/** 返回请求进入玛萨拉星副本结果 */
	public static final int	RESP_PLAYER_ENTER_MSLX_MISSION			= 14012;
	/*** 请求结束玛萨拉星 副本信息 */
	public static final int	REQ_PLAYER_ENd_MSLX_MISSION				= 14013;
	/*** 请求进入玛萨拉星 抽宝箱 */
	public static final int	REQ_PLAYER_ENTER_MSLX_CHEST_MISSION		= 14015;
	/*** 返回玩家登录时玛萨拉星副本信息 */
	public static final int	RESP_PLAYER_MSLX_MISSIONS_INFO			= 14016;
	/** 请求重置玛萨拉星副本 */
	public static final int	REQ_PLAYER_REVERT_MSLX_MISSION			= 14017;
	public static final int	RESP_PLAYER_REVERT_MSLX_MISSION			= 14018;
	/** 返回获得物品 */
	public static final int	RESP_PLAYER_REWARD_ITEM_MISSION			= 14020;
	/** 请求玛萨拉星商店刷新信息 */
	public static final int	REQ_PLAYER_MSLX_REFRESH_SHOP			= 14022;
	/** 返回玛萨拉星战斗后的机甲属性 */
	public static final int	RESP_PLAYER_MSLX_MECHA_PROP				= 14040;
	/* 请求显示玛萨拉星副本信息* */
	public static final int	REQ_PLAYER_MSLX_DISPLAYER_MISSION_INFO	= 14041;
	/* 返回显示玛萨拉星副本信息* */
	public static final int	RESP_PLAYER_MSLX_DISPLAYER_MISSION_INFO	= 14042;
	/***
	 * 新手引导与功能开放
	 */
	/** 请求进入新手引导 （只在动画引导的时候用） */
	public static final int	REQ_PLAYER_GUIDE_ID						= 14027;
	/** 返回玩家新手引导ID */
	public static final int	RESP_PLAYER_GUIDE_ID					= 14028;
	/** 角色功能开放引导 */
	public static final int	RESP_PLAYER_GUIDE_ROLE_OPEN				= 14030;
	/** 请求触发事件引导 */
	public static final int	REQ_PLAYER_TRIGGER_EVENT				= 14032;
	/** 请求事件检测引导 */
	public static final int	REQ_PLAYER_CHECK_TRIGGER_EVENT			= 14034;
	/** 返回事件检测引导 */
	public static final int	RESP_PLAYER_CHECK_TRIGGER_EVENT			= 14035;
	/** 完成新手引导（通过前端窗口ID） */
	public static final int	REQ_FINISH_PALYER_GUIDE					= 14036;
	public static final int	REQ_FNIISH_GUIDE_MISSION				= 14038;

	/** 返回玩家竞技场信息 */
	public static final int	RESP_PLAYER_ARENA_INFO					= 15001;
	/** 请求刷新竞技场 */
	public static final int	REQ_PLAYER_ARENA_REFRESH				= 15002;
	/** 请求挑战 */
	public static final int	REQ_PLAYER_ARENA_CHALLENGE				= 15003;
	/** 请求增加竞技场挑战次数 */
	public static final int	REQ_PLAYER_ARENA_ADD_CHALLENGE_NUM		= 15004;
	/** 请求清除竞技场CD */
	public static final int	REQ_PLAYER_ARENA_CLEAR_CD				= 15005;
	/** 请求排行榜列表 */
	public static final int	REQ_PLAYER_ARENA_RANK_LIST				= 15006;
	/** 返回排行榜列表 */
	public static final int	RESP_PLAYER_ARENA_RANK_LIST				= 15007;
	/** 请求排行榜个人详情 */
	public static final int	REQ_PLAYER_ARENA_RANK_DETAIL			= 15008;
	/** 返回竞技场个人详情 */
	public static final int	RESP_PLAYER_ARENA_RANK_DETAIL			= 15009;
	/** 请求领取竞技场奖励 */
	public static final int	REQ_PLAYER_ARENA_GET_REWARD				= 15011;

	/** 请求玩家商店数据 */
	public static final int	REQ_PLAYER_SHOP							= 15100;
	/** 返回玩家商店数据 */
	public static final int	RESP_PLAYER_SHOP						= 15101;
	/** 请求购买商店物品 */
	public static final int	REQ_PLAYER_BUY_SHOP_ITEM				= 15102;
	/** 返回更新商店物品 */
	public static final int	RESP_PLAYER_UPDATE_SHOP_ITEM			= 15103;
	/** 请求刷新商店数据 */
	public static final int	REQ_PLAYER_SHOP_REFRESH					= 15010;

	/** 请求刷新联邦补给站 */
	public static final int	REQ_REFRESH_DEPOT_SHOP					= 15200;

	/** 返回倒计时数据 */
	public static final int	RESP_COUNT_DOWN_TIME					= 15300;
	/** 请求购买资源 */
	public static final int	REQ_BUY_RESOURCE						= 15301;
	/** 返回购买资源 */
	public static final int	RESP_BUY_RESOURCE						= 15302;

	/** 返回计数器 */
	public static final int	RESP_COUNTER							= 15400;

	/** 返回玩家每日活动数据 */
	public static final int	RESP_PLAYER_ACTIVITYS					= 16001;
	/** 请求领取奖励 */
	public static final int	REQ_PLAYER_ACTIVITY_GET_AWARD			= 16002;

	/** 请求进入抽奖 */
	public static final int	REQ_PLAYER_LOTTERY						= 17001;
	/** 返回抽奖页面 */
	public static final int	RESP_PLAYER_LOTTERY						= 17002;
	/** 请求抽奖 */
	public static final int	REQ_PLAYER_LOTTERYING					= 17003;
	/** 返回抽奖奖励 */
	public static final int	RESP_PLAYER_LOTTERYING					= 17004;
	/** 请求战斗抽奖 */
	public static final int	REQ_PLAYER_FIGHT_LOTTERY				= 17005;

	/** 请求签到 */
	public final static int	REQ_SIGN								= 18001;
	/** 签到返回信息 */
	public final static int	RESP_SIGN								= 18101;
	/** 获取签到ID返回 */
	public final static int	RESP_GET_SIGN							= 18102;

	/** 返回城市玩家信息 */
	public final static int	RESP_PLAYER_IN_CITY						= 19000;
	/** 请求玩家移动 */
	public final static int	REQ_PLAYER_MOVE							= 19001;
	/** 返回玩家移动 */
	public final static int	RESP_PLAYER_MVOE						= 19002;
	/** 移除城市中的玩家 */
	public final static int	RESP_REMOVE_PLAYER						= 19003;

	/** 请求释放技能 */
	public final static int	REQ_FIGHT_CAST_SKILL					= 20000;
	/** 返回战斗行为 */
	public final static int	RESP_FIGHT_ACTION						= 20001;
	/** 返回战斗对象的属性 */
	public final static int	RESP_FIGHTER_PROPS						= 20002;
	/** 返回当前游戏状态 */
	public final static int	RESP_FIGHT_STATE						= 20003;
	/** 请求继续下一波 */
	public final static int	REQ_FIGHT_NEXT_WAVE						= 20004;
	/** 返回切换波次 */
	public final static int	RESP_CHANGE_WAVE						= 20005;
	/** 返回护盾信息 */
	public final static int	RESP_FIGHT_SHIELD_INFO					= 20006;
	/** 返回属性修改信息 */
	public final static int	RESP_FIGHT_PROP_ALTTER_INFO				= 20007;
	/** 请求切换自动释放技能 */
	public final static int	REQ_FIGHT_TOOGLE_AUTO_SKILL				= 20008;

	/** 请求进入特殊技模式 */
	public final static int	REQ_FIGHT_SPECIAL_SKILL_START			= 20009;
	/** 返回进入特殊技模式 */
	public final static int	RESP_FIGHT_SPECIAL_SKILL_START			= 20010;
	/** 请求结束特殊技模式 */
	public final static int	REQ_FIGHT_SPECIAL_SKILL_END				= 20011;
	/** 返回结束特殊技模式 */
	public final static int	RESP_FIGHT_SPECIAL_SKILL_END			= 20012;

	/** 请求打开邮箱 */
	public final static int	REQ_PLAYER_MAILS_LIST					= 21000;
	/** 返回邮件列表 */
	public final static int	RESP_PLAYER_MAILS_LIST					= 21001;
	/** 请求打开邮件 */
	public final static int	REQ_PLAYER_MAIL_OPEN					= 21002;
	/** 返回邮件内容 */
	public final static int	RESP_PLAYER_MAIL_CONTENT				= 21003;
	/** 请求接收附件 */
	public final static int	REQ_PLAYER_MAIL_RECV_ATTACHMENT			= 21004;
	/** 是否有附件领取 */
	public final static int	RESP_PLAYER_MAILS_HAS_ATTACH			= 21005;

	/** 请求创建队伍 */
	public final static int	REQ_CREATE_TEAM							= 23000;
	/** 返回队伍信息 */
	public final static int	RESP_TEAM_INFO							= 23001;
	/** 請求加入队伍 */
	public final static int	REQ_JOIN_TEAM							= 23002;
	/** 请求准备/开始 */
	public final static int	REQ_TEAM_PREPARE_OR_START				= 23003;
	/** 请求踢人 */
	public final static int	REQ_TEAM_KICK_PLAYER					= 23004;
	/** 返回给被踢的人 */
	public final static int	RESP_TEAM_LEAVE							= 23005;
	/** 请求解散或离开 */
	public final static int	REQ_TEAM_DISMISS_OR_LEAVE				= 23006;
	/** 请求广播邀请信息 */
	public final static int	REQ_TEAM_BROADCAST_INVITE				= 23007;
	/** 返回广播结果 */
	public final static int	RESP_TEAM_BROADCAST_INVITE				= 23008;
	/** 请求设置队伍波次 */
	public final static int	REQ_TEAM_SET_WAVE						= 23009;
	/** 请求预设置队伍波次 */
	public final static int	REQ_TEAM_PRE_SET_WAVE					= 23010;
	/** 返回预设置队伍波次 */
	public final static int	RESP_TEAM_PRE_SET_WAVE					= 23011;
	/** 返回设置队伍波次 */
	public final static int	RESP_TEAM_SET_WAVE						= 23012;
	/** 请求一键设置队伍波次 */
	public final static int	REQ_TEAM_SET_ONE_KEY					= 23013;
	/** 返回一键设置队伍波次 */
	public final static int	RESP_TEAM_SET_ONE_KEY					= 23014;

	/** 请求提交组队副本奖励游戏信息 */
	public final static int	REQ_TEAM_MISSION_AWARD_GAME_INFO		= 23015;
	/** 返回组队副本奖励游戏信息 */
	public final static int	RESP_TEAM_MISSION_AWARD_GAME_INFO		= 23016;
	/** 返回组队副本奖励游戏奖励 */
	public final static int	RESP_TEAM_MISSION_AWARD_GAME_AWARD_ITEM	= 23017;
	/** 请求组队副本协助者通用奖励 */
	public final static int	REQ_TEAM_MISSION_COMMON_AWARD			= 23018;
	/** 请求组队副本协助奖励 */
	public final static int	REQ_TEAM_ASSIST_AWARD					= 23020;
	/** 返回组队副本协助奖励 */
	public final static int	RESP_TEAM_ASSIST_AWARD					= 23021;
	/** 请求获取组队副本协助奖励 */
	public final static int	REQ_TEAM_ASSIST_AWARD_GET				= 23022;

	/**
	 * 公会
	 */
	/** 请求创建公会 */
	public final static int	REQ_CREATE_SOCIETY						= 23031;
	/** 返回创建公会 */
	public final static int	RESP_CREATE_SOCIETY						= 23032;
	/** 请求批准或者拒绝公会 */
	public final static int	REQ_JOIN_SOCIETY						= 23033;
	/** 返回批准或者拒绝公会 */
	public final static int	RESP_JOIN_SOCIETY						= 23034;
	/** 请求打开公会界面 */
	public final static int	REQ_OPEN_SOCIETY						= 23035;
	/** 返回打开公会界面 */
	public final static int	RESP_OPEN_SOCIETY						= 23036;
	/** 请求退出公会 */
	public final static int	REQ_EXIT_SOCIETY						= 23037;
	/** 返回退出公会 */
	public final static int	RESP_EXIT_SOCIETY						= 23038;
	/** 请求公会签到 */
	public final static int	REQ_QIANDAO_SOCIETY						= 23039;
	/** 返回公会签到 */
	public final static int	RESP_QIANDAO_SOCIETY					= 23040;
	/** 请求公会踢出 */
	public final static int	REQ_KICK_SOCIETY						= 23041;
	/** 返回公会踢出 */
	public final static int	RESP_KICK_SOCIETY						= 23042;
	/** 请求修改公会信息（入会需求，公告） */
	public final static int	REQ_UPDATE_DATA							= 23043;
	/** 返回修改公会信息（入会需求，公告） */
	public final static int	RESP_UPDATE_DATA						= 23044;
	/** 请求申请加入公会 */
	public final static int	REQ_APPLY_JOIN							= 23045;
	/** 返回申请加入公会 */
	public final static int	RESP_APPLY_JOIN							= 23046;
	/** 请求查找公会 */
	public final static int	REQ_SEARCH_SOCIETY						= 23047;
	/** 返回查找公会 */
	public final static int	RESP_SEARCH_SOCIETY						= 23048;
	/** 请求申请加入公会列表 */
	public final static int	REQ_SEARCH_SOCIETY_LIST					= 23049;
	/** 返回申请加入公会列表 */
	public final static int	RESP_SEARCH_SOCIETY_LIST				= 23050;
	/** 请求公会成员申请加入列表 */
	public final static int	REQ_APPLY_JOIN_LIST						= 23051;
	/** 返回公会成员申请加入列表 */
	public final static int	RESP_APPLY_JOIN_LIST					= 23052;

	/** 请求打开公会雇佣兵列表(玩家雇佣兵界面) */
	public final static int	REQ_SOCIETY_MERCENARY_LIST				= 23053;
	/** 返回打开公会雇佣兵列表(玩家雇佣兵界面) */
	public final static int	RESP_SOCIETY_MERCENARY_LIST				= 23054;
	/** 请求派遣公会雇佣兵 */
	public final static int	REQ_SOCIETY_MERCENARY_DISPATCH			= 23055;
	/** 返回派遣公会雇佣兵 */
	public final static int	RESP_SOCIETY_MERCENARY_DISPATCH			= 23056;
	/** 请求公会雇佣兵归队 */
	public final static int	REQ_SOCIETY_MERCENARY_BACK				= 23057;
	/** 返回公会雇佣兵归队 */
	public final static int	RESP_SOCIETY_MERCENARY_BACK				= 23058;
	/** 请求打开公会雇佣兵列表(公会所有雇佣兵界面) */
	public final static int	REQ_SOCIETY_MERCENARY_ALL_LIST			= 23059;
	/** 返回打开公会雇佣兵列表(公会所有雇佣兵界面) */
	public final static int	RESP_SOCIETY_MERCENARY_ALL_LIST			= 23060;

	/**
	 * 小行星带猎场
	 */
	/** 打开小行星带猎场主界面 **/
	public final static int	REQ_OPEN_HUNTGROUND_UI					= 24001;
	/** 返回打开小行星带猎场主界面 */
	public final static int	RESP_OPEN_HUNTGROUND_UI					= 24002;
	/** 打开小行星带猎场主界面 **/
	public final static int	REQ_LOOT_HUNTGROUND						= 24003;
	/** 返回打开小行星带猎场主界面 */
	public final static int	RESP_LOOT_HUNTGROUND					= 24004;
	/** 小行星带猎场抢夺玩家 **/
	public final static int	REQ_HUNTGROUND_TO_LOOT					= 24005;
	/** 返回小行星带猎场抢夺玩家 */
	public final static int	RESP_HUNTGROUND_TO_LOOT					= 24006;
	/** 打开小行星带猎场抢夺记录 **/
	public final static int	REQ_HUNTGROUND_LOOT_RECORD_LIST			= 24007;
	/** 返回打开小行星带猎场抢夺记录 */
	public final static int	RESP_HUNTGROUND_LOOT_RECORD_LIST		= 24008;
	/** 删除小行星带猎场抢夺记录 **/
	public final static int	REQ_DELETE_HUNTGROUND_LOOT_RECORD		= 24009;
	/** 返回删除小行星带猎场抢夺记录 */
	public final static int	RESP_DELETE_HUNTGROUND_LOOT_RECORD		= 24010;
	/** 小行星带猎场抢夺亲自复仇 **/
	public final static int	REQ_HUNTGROUND_LOOT_REVENGE_OWN			= 24011;
	/** 返回小行星带猎场抢夺亲自复仇 */
	public final static int	RESP_HUNTGROUND_LOOT_REVENGE_OWN		= 24012;
	/** 小行星带猎场抢夺公会复仇 **/
	public final static int	REQ_HUNTGROUND_LOOT_REVENGE_SOCIETY		= 24013;
	/** 返回小行星带猎场抢夺公会复仇 */
	public final static int	RESP_HUNTGROUND_LOOT_REVENGE_SOCIETY	= 24014;
	/** 小行星带猎场购买能量石 **/
	public final static int	REQ_HUNTGROUND_BUY_ENEGYNUMS			= 24015;
	/** 返回小行星带猎场购买能量石 */
	public final static int	RESP_HUNTGROUND_BUY_ENEGYNUMS			= 24016;
	/** 小行星带猎场使用触发系统保护道具 **/
	public final static int	REQ_HUNTGROUND_USE_PRODUCT_ITEM			= 24017;
	/** 返回小行星带猎场使用触发系统保护道具 */
	public final static int	RESP_HUNTGROUND_USE_PRODUCT_ITEM		= 24018;
	/** 小行星带猎场发送公会复仇协助信息 **/
	public final static int	REQ_SEND_HUNTGROUND_REVEGEN_MSG			= 24019;
	/** 返回小行星带猎场发送公会复仇协助信息 */
	public final static int	RESP_SEND_HUNTGROUND_REVEGEN_MSG		= 24020;
	/** 小行星带猎场发送查看对方信息 **/
	public final static int	REQ_LOOK_PLAYER_DETAIL					= 24021;
	/** 返回小行星带猎场对方信息 */
	public final static int	RESP_LOOK_PLAYER_DETAIL					= 24022;

	/**
	 * 奖励活动 新手签到 首充 消费 VIP有礼等等开始
	 */
	/** 打开活动主界面 **/
	public final static int	REQ_OPEN_REWARD_ACTIVITY_MAIN			= 25001;
	/** 返回打开活动主界面 */
	public final static int	RESP_OPEN_REWARD_ACTIVITY_MAIN			= 25002;
	/** 打开新手签到界面 **/
	public final static int	REQ_OPEN_REWARD_ACTIVITY_QIANDAO_MAIN	= 25003;
	/** 返回打开新手签到界面 */
	public final static int	RESP_OPEN_REWARD_ACTIVITY_QIANDAO_MAIN	= 25004;
	/** 请求新手签到 **/
	public final static int	REQ_QIANDAO								= 25005;
	/** 返回新手签到 */
	public final static int	RESP_QIANDAO							= 25006;
	/** 请求打开vip礼包界面 **/
	public final static int	REQ_OPEN_VIPLIBAO_UI					= 25007;
	/** 返回打开vip礼包界面 */
	public final static int	RESP_OPEN_VIPLIBAO_UI					= 25008;
	/** 请求购买VIP礼包 **/
	public final static int	REQ_BUY_VIP_LIBAO						= 25009;
	/** 返回购买VIP礼包 */
	public final static int	RESP_BUY_VIP_LIBAO						= 25010;
	/** 请求打开消费送礼界面 **/
	public final static int	REQ_OPEN_COMSUME_UI						= 25011;
	/** 返回打开消费送礼界面 */
	public final static int	RESP_OPEN_COMSUME_UI					= 25012;
	/** 请求领取消费送礼奖励 **/
	public final static int	REQ_RECEIVE_COMSUME_REWARD				= 25013;
	/** 返回领取消费送礼奖励 */
	public final static int	RESP_RECEIVE_COMSUME_REWARD				= 25014;
	/** 请求打开首充界面 **/
	public final static int	REQ_OPEN_FIRST_RECHARGE					= 25015;
	/** 返回打开首充界面 */
	public final static int	RESP_OPEN_FIRST_RECHARGE				= 25016;
	/** 请求领取首充奖励 **/
	public final static int	REQ_RECEIVE_FIRST_RECHARGE				= 25017;
	/** 返回领取首充奖励 */
	public final static int	RESP_RECEIVE_FIRST_RECHARGE				= 25018;
	/** 请求打开成长基金界面 **/
	public final static int	REQ_OPEN_GROW_JJ_UI						= 25019;
	/** 返回打开成长基金界面 */
	public final static int	RESP_OPEN_GROW_JJ_UI					= 25020;
	/** 请求购买成长基金 **/
	public final static int	REQ_BUY_GROW_JJ							= 25021;
	/** 返回购买成长基金 */
	public final static int	RESP_BUY_GROW_JJ						= 25022;
	/** 请求领取成长基金 **/
	public final static int	REQ_RECEIVE_GROW_JJ						= 25023;
	/** 返回领取成长基金 */
	public final static int	RESP_RECEIVE_GROW_JJ					= 25024;
	/** 请求打开充值界面 **/
	public final static int	REQ_OPEN_RECHARGE_UI					= 25025;
	/** 返回打开充值界面 */
	public final static int	RESP_OPEN_RECHARGE_UI					= 25026;
	/** 请求充值钻石 **/
	public final static int	REQ_RECHARGE_DIAMOND					= 25027;
	/** 返回充值钻石 */
	public final static int	RESP_RECHARGE_DIAMOND					= 25028;
	/** 请求领取签到的额外奖励 **/
	public final static int	REQ_RECEIVE_ETRA_QIANDAO				= 25029;
	/** 返回领取签到的额外奖励 */
	public final static int	RESP_RECEIVE_ETRA_QIANDAO				= 25030;
	/** 返回活动红点标记 */
	public final static int	RESP_ACTIVITY_STATUS_SHOW				= 25031;
	/** 返回消费有礼今天的消费额 */
	public final static int	RESP_ACTIVITY_CONMUSE_AMOUNT			= 25032;

	/**
	 * 奖励活动 新手签到 首充 消费 VIP有礼等等结束
	 * */

	/**
	 * 新功能开发
	 */
}
