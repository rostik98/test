<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="addRoom" method="post">
	<label for="namee">Enter name of new room :</label> <input name="namee">
	<br>
	<label for="square">Enter max square of new room :</label> <input name="square">
	<br>
	
	<label for="noofcoords">Enter the number of coordinate pairs :</label>
	<br>
  <input name="noofcoords" type="text"><br/><br/>
  <a href="#" id="names" onclick="addFields();">add points</a>
  <div id='container' >
  </div>
  <br/><br/>
	<input type="submit" value="SaveRoom">
</form>

<script>
	function addFields() {
	  debugger;
	  var arr = [];
	  arr.push()
	  var number = document.getElementsByName("noofcoords")[0].value;
	  var container = document.getElementById("container");
	  container.innerHTML = '';
	  for (i = 1; i <= number; i++) {
	    container.appendChild(document.createTextNode("X" + i));
	    var inputX = document.createElement("input");
	    var inputY = document.createElement("input");
	    inputX.type = "text";
	    inputX.name = "X" + i;
	    inputY.type = "text";
	    inputY.name = "Y" + i;
	    container.appendChild(inputX);
	    container.appendChild(document.createTextNode("Y" + i));
	    container.appendChild(inputY);
	    container.appendChild(document.createElement("br"));
	  }
	}
</script>

</body>
</html>