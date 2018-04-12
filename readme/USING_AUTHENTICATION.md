![alt text](https://cftechsol.com/wp-content/uploads/2017/12/caiofrota-logo-300x171.png)

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)

# CF Rest

### Using Authentication

* Request

```
curl -u myusername:mypassword http://localhost:8080/login
```

* Response

```
Status: 200
Authorization: Bearer eyJjYuciOiHyRzUxMiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6ImFkbWluQGNmdGVjaHNvbC5jb20iLCJleHAiOjE1MjM1MDQxMTR9.TpHANYQYSAOiUysVNJ-RazJ10BGtwYuVZelOIAlozW1vHG4s7CGi906kzQ4_rq_-OiK_kWpY4UsGUifTs1z2LQ
```

You'll get 401 when username or password is invalid.
You'll get 500 when username and password is not present.

[Back to REAMDE.md](https://github.com/caiofrota/cf-rest)
