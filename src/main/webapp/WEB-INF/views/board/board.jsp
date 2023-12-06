<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>board TEST</title>
</head>
<body>
	<header>
		<!-- Jumbotron -->
		<div id="intro" class="p-5 text-center bg-light">
			<h1 class="mb-0 h4">This is a long title of the article</h1>
		</div>
		<!-- Jumbotron -->
	</header>

	<!--Main layout-->
	<main class="mt-4 mb-5">
		<div class="container">
		<!--Grid row-->
		<div class="row">
      		<table class="table table-hover">
      			<colgroup>
      				<col width="60px;" class="xs_none">
      				<col>
      				<col width="10%;">
      				<col width="20%;">
      				<col width="60px;" class="xs_none">
      			</colgroup>
      			<thead>
      				<tr>
      					<th scope="col" class="xs_none">No</th>
      					<th scope="col">title</th>
      					<th scope="col">user</th>
      					<th scope="col">time</th>
      					<th scope="col" class="xs_none">cnt</th>
      				</tr>
      			</thead>
      			<tbody class="table-group-divider table-divider-color">
      				<tr>
      					<td scope="row" class="xs_none">1001</td>
      					<td>3234523462</td>
      					<td>32</td>
      					<td>12</td>
      					<td class="xs_none">1</td>
      				</tr>
      				<tr>
      					<td scope="row" class="xs_none">1000</td>
      					<td>3234523462</td>
      					<td>32</td>
      					<td>12</td>
      					<td class="xs_none">1</td>
      				</tr>
      				<tr>
      					<td scope="row" class="xs_none">999</td>
      					<td>3234523462</td>
      					<td>32</td>
      					<td>12</td>
      					<td class="xs_none">1</td>
      				</tr>
      			</tbody>
      		</table>
		</div>
      
		<!--Grid row-->
    </div>
  </main>
  <!--Main layout-->
  
	<div id="app">
		<input v-model="message">
		<p>{{ message }}</p>
	</div>

<script>
	var vm = new Vue({
	    el: '#app',
	    data: {
			message: 'Vue.js! TEST'
	    }
	});
</script>
</body>
