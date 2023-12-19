<!doctype html>
<html lang="en">
    <head>
        <title>Register page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body class="img js-fullheight w-100"  style="background-image: url(images/bg-intro-01.jpg); height: 100vh">
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <div class="login-wrap p-0">
                            <h3 class="mb-4 text-center">Register page</h3>
                            <form class="signin-form" action="" method="POST">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Username" name="username" required>
                                </div>
                                <div class="form-group">
                                    <input id="email-field" type="email" name="email" class="form-control" placeholder="Email" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" name="password" class="form-control" placeholder="Password" required>
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group">
                                    <input id="confirm-password-field" type="password" class="form-control" placeholder="Confirm Password" required>
                                    <span toggle="#confirm-password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>

                                <div class="form-group">
                                    <button type="button" class="form-control btn btn-primary submit px-3 btn-sign-up">Sign Up</button>
                                </div>
                            </form>
                            <p class="w-100 text-center">&mdash; Or login <a href="./login">Here</a> &mdash;</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main_login.js"></script>

        <script type="text/javascript">
            const inputPassword = document.querySelector("#password-field");
            const inputConfirmPassword = document.querySelector("#confirm-password-field");
            const buttonSignUp = document.querySelector(".btn-sign-up");
            const formSignUp = document.querySelector(".signin-form");
            const inputUsername = document.querySelector("input[name='username']");
            const inputEmail = document.querySelector("input[name='email']");



            buttonSignUp.addEventListener("click", () => {
                let isSubmit = true;
                
                if (inputUsername.value.includes(" ")) {
                    isSubmit = false;
                    alert("Username cannot contain spaces !!!");
                }

                if (inputPassword.value !== inputConfirmPassword.value) {
                    isSubmit = false;
                    alert("Mismatched password !!!");
                }
                
                if (!inputEmail.value.includes("@")) {
                    isSubmit = false;
                    alert("Email not valid !!!");
                }

                if (isSubmit) {
                    formSignUp.submit();
                }
            })
        </script>
    </body>
</html>

