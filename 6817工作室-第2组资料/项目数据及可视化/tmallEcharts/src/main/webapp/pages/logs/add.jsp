<%@ page language="java" contentType="text/html; charset=utf-8"
		 isELIgnored="false"
		 pageEncoding="utf-8"%>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<article class="page-container">
		<div style="width:100%;text-align:center">
			<form action="/add" method="post" class="horizontal-form" >

				<div class="row-fluid">
					<label class="control-label">编号</label>
					<div class="controls" >
						<input type="text" name="id"  >
					</div>
				</div>

				<div class="row-fluid">
					<label class="control-label">状态</label>
					<div class="controls">
						<input type="text"  name="status" >
					</div>
				</div>

				<div class="row-fluid">
					<label class="control-label">纬度</label>
					<div class="controls">
						<input type="text"  name="latitude" >
					</div>
				</div>

				<div class="row-fluid">
					<label class="control-label">经度</label>
					<div class="controls">
						<input type="text"  name="longitude" >
					</div>
				</div>

				<div class="row-fluid">
					<label class="control-label">省</label>
					<div class="controls">
						<input type="text"  name="province" >
					</div>
				</div>

				<div class="row-fluid">
					<label class="control-label">市</label>
					<div class="controls">
						<input type="text"  name="city" >
					</div>
				</div>

				<div class="row-fluid">
					<label class="control-label">区</label>
					<div class="controls">
						<input type="text"  name="country">
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn blue">
						<i class="icon-ok"></i> 添加
					</button>
					<button type="button" class="btn" ><a href="/bike_list" style="text-decoration: none;color: #1F1F1F">取消</a></button>
				</div>


			</form>
		</div>


</article>

</body>
</html>