$(function(){
	//	左侧菜单栏
	$(".left-sidebar-con dd").hide();
	// $(".left-sidebar-con dl:eq(0) dd").show();
	$(".left-sidebar-con dl dt").click(function(){
		//折叠元素显示隐藏
		$(this).next('dd').slideToggle();
		$(this).parent().siblings().find('dd').slideUp();
		//切换箭头图片
		var that = this;
		var src = $(this).find('img');
		if(src.attr('src')=='../img/left_down_r.png'){
			src.attr('src','../img/left_down_b.png');
			$(that).parent().siblings().find("dt img").attr('src','../img/left_down_r.png');
		}else{
			src.attr('src','../img/left_down_r.png');
		}
	});
	//折叠元素中选中选中样式
	$(".left-sidebar-con dl li a").click(function(){
		$(this).addClass('current').parent().siblings().children().removeClass('current');
	});
})