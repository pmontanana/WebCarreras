<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background: rgb(2,0,36);
      background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 35%, rgba(0,212,255,1) 100%);
    }
    .login-container {
      background-color: #06e290;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .login-container h2 {
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<div class="login-container">
  <h2>Login</h2>
  <form action="Login" method="post">
    <div class="mb-3">
      <label for="user" class="form-label">Username:</label>
      <input type="text" id="user" name="user" class="form-control" required>
    </div>
    <div class="mb-3">
      <label for="clave" class="form-label">Password:</label>
      <input type="password" id="clave" name="clave" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
  <% if (request.getParameter("error") != null) {%>
  <p class="text-danger mt-3">Invalid username or password. Please try again.</p>
  <%}%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>