<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
</head>
<body>

	<h3>Ajax File Upload</h3>
	<div class='fileDrop'></div>

	<div class='uploadedList'></div>

	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});

		$(".fileDrop")
				.on(
						"drop",
						function(event) {
							event.preventDefault();

							var files = event.originalEvent.dataTransfer.files;

							var file = files[0];

							//console.log(file);

							var formData = new FormData();

							formData.append("file", file);

							$
									.ajax({
										url : '/uploadAjax',
										data : formData,
										dataType : 'text',
										processData : false,
										contentType : false,
										type : 'POST',
										success : function(data) {

											var str = "";

											//data = "/2021/07/07/s_~.jpg"  이미지에는 앞에 s가 들어간다.
											if (checkImageType(data)) {
												str = "<div><a href=displayFile?fileName="
														+ getImageLink(data)
														+ ">" //이미지가 폴더안에 있으면 평소처럼 a href="img/s_dog.jpg"라고 해도 되지만
														//우리 이미지는 c드라이브 밑에 들어가 있어서 저렇게 쓸 수 없고 이렇게 써야함.
														
														+ "<img src='displayFile?fileName="
														+ data
														+ "'/>"
														+ "</a><small data-src="+data+">X</small></div>";
														//썸네일을 x누르면 원본과 썸네일 모두 폴더에서 사라져 있는걸 볼 수 있음.
											} else {
												str = "<div><a href='displayFile?fileName="
														+ data
														+ "'>"
														+ getOriginalName(data)
														+ "</a>"
														+ "<small data-src="+data+">X</small></div></div>";
											}

											$(".uploadedList").append(str);
										}
									});
						});

		$(".uploadedList").on("click", "small", function(event) {

			var that = $(this);

			$.ajax({
				url : "deleteFile",
				type : "post",
				data : {
					fileName : $(this).attr("data-src")
				},
				dataType : "text",
				success : function(result) {
					if (result == 'deleted') {
						that.parent("div").remove();
					}
				}
			});
		});

		/* 		
		 $(".fileDrop").on("drop", function(event) {
		 event.preventDefault();
		
		 var files = event.originalEvent.dataTransfer.files;
		
		 var file = files[0];

		 //console.log(file);
		 var formData = new FormData();
		
		 formData.append("file", file);

		
		 $.ajax({
		 url: '/uploadAjax',
		 data: formData,
		 dataType:'text',
		 processData: false,
		 contentType: false,
		 type: 'POST',
		 success: function(data){
		
		 var str ="";
		
		 console.log(data);
		 console.log(checkImageType(data));
		
		 if(checkImageType(data)){
		 str ="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
		 +"<img src='displayFile?fileName="+data+"'/></a>"
		 +data +"</div>";
		 }else{
		 str = "<div><a href='displayFile?fileName="+data+"'>" 
		 + getOriginalName(data)+"</a></div>";
		 }
		
		 $(".uploadedList").append(str);
		 }
		 });			
		 });	 */

		function getOriginalName(fileName) {

			if (checkImageType(fileName)) {
				return;
			}

			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);

		}

		function getImageLink(fileName) {

			if (!checkImageType(fileName)) {
				return;
			}
			var front = fileName.substr(0, 12); //0부터 12까지의 글자열 
			var end = fileName.substr(14); //앞에서부터 14개의 글자열

			return front + end; //전체 리턴

		}

		/* 		$(".fileDrop").on("drop", function(event) {
		 event.preventDefault();
		
		 var files = event.originalEvent.dataTransfer.files;
		
		 var file = files[0];

		 //console.log(file);
		 var formData = new FormData();
		
		 formData.append("file", file);
		
		 $.ajax({
		 url: '/uploadAjax',
		 data: formData,
		 dataType:'text',
		 processData: false,
		 contentType: false,
		 type: 'POST',
		 success: function(data){
		
		 alert(data);
		
		 }
		 });
		
		 }); */

		//fileName = "/2021/07/07/~~.jpg"
		function checkImageType(fileName) {

			var pattern = /jpg|gif|png|jpeg/i;

			return fileName.match(pattern);

		}
	</script>

</body>
</html>