/* function that creates AJAX request to servlet that deletes profit on button click*/
$(document).ready(function () {
    $(".btnProfitDelete").click(function (event) {
        event.preventDefault();
        var profitID = event.target.id.replace( /[^\d.]/g, '' ); //btn id contains id of profit to delete - get it
        var isProfit = "true";

        // check if user realise what to do
        if(!confirm("Are you really want to delete this profit?"))
        {
            return;
        }

        $.ajax({
            url : "deleteCashFlow",
            type : "POST",
            data : {
                isProfit : isProfit,
                cashFlowID : profitID
            },
            // if success - remove table row with profit that has just been deleted - else - error message
            success : function (responseText) {
                if(responseText === "success"){
                    var deleteID = "#trProfit" + profitID;
                    $(deleteID).remove();
                    alert("Good response - Profit is removed!");
                }
                else
                {
                    alert("Bad response! " + responseText);
                }
            }
        });
    })
})

/* function that creates AJAX request to servlet that deletes spending on button click*/
$(document).ready(function () {
    $(".btnSpendingDelete").click(function (event) {
        event.preventDefault();
        var spendingID = event.target.id.replace( /[^\d.]/g, '' );//btn id contains id of spending to delete - get it
        var isProfit = "false";

        // check if user realise what to do
        if(!confirm("Are you really want to delete this spending?"))
        {
            return;
        }

        $.ajax({
            url : "deleteCashFlow",
            type : "POST",
            data : {
                isProfit : isProfit,
                cashFlowID : spendingID
            },
            // if success - remove table row with spending that has just been deleted - else - error message
            success : function (responseText) {
                if(responseText === "success"){
                    var deleteID = "#trSpending" + spendingID;
                    $(deleteID).remove();
                    alert("Good response - Spending is removed!");
                }
                else
                {
                    alert("Bad response! " + responseText);
                }
            }
        });
    })
})