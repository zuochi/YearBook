function fullAvatarEditor() {
	var id				= 'fullAvatarEditor'			//flash文件的ID
	var file			= 'swf/fullAvatarEditor.swf';		//flash文件的路径
	var	version			= "10.1.0";						//播放该flash所需的最低版本
	var	expressInstall	= 'swf/expressInstall.swf';			//expressInstall.swf的路径
	var	width			= 630;							//flash文件的宽度
	var	height			= 430;							//flash文件的高度
	var container		= id;							//装载flash文件的容器(如div)的id
	var flashvars		= {};
	var callback		= function(){};
	var heightChanged	= false;
	//智能获取参数，字符类型为装载flash文件的容器(如div)的id，第一个数字类型的为高度，第二个为宽度，第一个object类型的为参数对象，如此4个参数的顺序可随意。
	for(var i = 0; i < arguments.length; i++)
	{
		if(typeof arguments[i] == 'string')
		{
			container = arguments[i];
		}
		else if(typeof arguments[i] == 'number')
		{
			if(heightChanged)
			{
				width = arguments[i];
			}
			else
			{
				height = arguments[i];
				heightChanged = true;
			}
		}
		else if(typeof arguments[i] == 'function')
		{
			callback = arguments[i];
		}
		else
		{
			flashvars = arguments[i];
		}
	}
	var vars = {
		id : id
	};
	//合并参数
	for (var name in flashvars)
	{
		if(flashvars[name] != null)
		{
			if(name == 'upload_url' || name == 'src_url')
			{
				vars[name] = encodeURIComponent(flashvars[name]);
			}
			else
			{
				vars[name] = flashvars[name];
			}
		}
	}
	var params = {
		menu				: 'true',
		scale				: 'noScale',
		allowFullscreen		: 'true',
		allowScriptAccess	: 'always',
		wmode				: 'transparent'
	};
	var attributes = {
		id	: vars.id,
		name: vars.id
	};
	var swf = null;
	var	callbackFn = function (e) {
		swf = e.ref;
		swf.eventHandler = function(json){
			callback.call(swf, json);
		};
	};
	swfobject.embedSWF(
		file, 
		container,
		width,
		height,
		version,
		expressInstall,
		vars,
		params, 
		attributes,
		callbackFn
	);
	return swf;
}

swfobject.addDomLoadEvent(function () {
    var swf = new fullAvatarEditor("swfContainer", {
    	 id: 'swf',
			upload_url:'user/photoUpload_execute',
			src_upload:0,
			avatar_sizes:'150*150|50*50|32*32',
			avatar_sizes_desc:'150×150|50×50|32×32'

		}, function (msg) {
			switch(msg.code)
			{
				case 1 : //alert("页面成功加载了组件！");
							break;
				case 2 : //alert("已成功加载默认指定的图片到编辑面板。");
							break;
				case 3 :
					if(msg.type == 0)
					{
						alert("摄像头已准备就绪且用户已允许使用。");
					}
					else if(msg.type == 1)
					{
						alert("摄像头已准备就绪但用户未允许使用！");
					}
					else
					{
						alert("摄像头被占用！");
					}
				break;
				case 5 : 
					if(msg.type == 0)
					{
						window.parent.left.location.reload();
					}
				break;
			}
		}
	);
});
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F5f036dd99455cb8adc9de73e2f052f72' type='text/javascript'%3E%3C/script%3E"));