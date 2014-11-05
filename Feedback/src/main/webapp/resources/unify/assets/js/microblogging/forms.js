$("#feed-form").submit(function(e) {
	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action");
	$.ajax({
		url : formURL,
		type : "POST",
		data : postData,
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				$("#title").val("");
				$("#description").val("");
				$("#tag-cloud").empty();
				activeDrawingManager();
				// sincronizar mensaje!!
				synchronizer.syncMessage(data, "feed");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
	e.preventDefault();
});
$(".comment-form").live('submit', function(e) {
	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action");
	$.ajax({
		url : formURL,
		type : "POST",
		data : postData,
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				$(".comment").val("");
				// sincronizar mensaje!!
				synchronizer.syncMessage(data, "comment");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
	e.preventDefault();
});
$(".like-form").live('submit', function(e) {
	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action");
	$.ajax({
		url : formURL,
		type : "POST",
		data : postData,
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				// sincronizar mensaje!!
				synchronizer.syncMessage(data, "rating");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
	e.preventDefault();
});
$(".dislike-form").live('submit', function(e) {
	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action");
	$.ajax({
		url : formURL,
		type : "POST",
		data : postData,
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				// sincronizar mensaje!!
				synchronizer.syncMessage(data, "rating");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
	e.preventDefault();
});
$(".tag-form").live('submit', function(e) {
	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action");
	$.ajax({
		url : formURL,
		type : "POST",
		data : postData,
		success : function(data, textStatus, jqXHR) {
			if (data != "") {
				// sincronizar mensaje!!
				synchronizer.syncMessage(data, "tag");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
		}
	});
	e.preventDefault();
});