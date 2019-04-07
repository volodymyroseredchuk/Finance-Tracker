/* function that is triggered on btn click and shows or hides the form to change password */
function changePassword()
{
    // second button click will hide the form
    if(document.getElementById("hiddenChangePasswordForm").style.display === "block")
    {
        document.getElementById("hiddenChangePasswordForm").style.display = "none";
        return;
    }

    // only one of two choices can be available at the same moment
    if(document.getElementById("hiddenDeleteAccountForm").style.display === "block")
    {
        document.getElementById("hiddenDeleteAccountForm").style.display = "none"
    }
    document.getElementById("hiddenChangePasswordForm").style.display = "block";
}

/* function that is triggered on btn click and shows or hides the form to delete account */
function deleteAccount()
{
    // second button click will hide the form
    if(document.getElementById("hiddenDeleteAccountForm").style.display === "block")
    {
        document.getElementById("hiddenDeleteAccountForm").style.display = "none";
        return;
    }

    // only one of two choices can be available at the same moment
    if(document.getElementById("hiddenChangePasswordForm").style.display === "block")
    {
        document.getElementById("hiddenChangePasswordForm").style.display = "none"
    }
    document.getElementById("hiddenDeleteAccountForm").style.display = "block";
}