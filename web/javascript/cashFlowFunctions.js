/* function that is triggered on btn click and shows form to add new profit */
function addProfit()
{
    document.getElementById("hiddenAddProfit").style.display = "block";
}

/* function that is triggered on btn click and hides form to add new profit and clears input elements */
function addProfitCancel()
{
    document.getElementById("hiddenAddProfit").style.display = "none";

    document.getElementById("hiddenAddProfitDescription").setAttribute("value", "");
    document.getElementById("hiddenAddProfitCategory").setAttribute("value", "");
    document.getElementById("hiddenAddProfitValue").setAttribute("value", "");
}

/* function that is triggered on btn click and shows form to add new spending */
function addSpending()
{
    document.getElementById("hiddenAddSpending").style.display = "block";
}

/* function that is triggered on btn click and hides form to add new spending and clears input elements */
function addSpendingCancel()
{
    document.getElementById("hiddenAddSpending").style.display = "none";

    document.getElementById("hiddenAddSpendingDescription").setAttribute("value", "");
    document.getElementById("hiddenAddSpendingCategory").setAttribute("value", "");
    document.getElementById("hiddenAddSpendingValue").setAttribute("value", "");
}

/* function that is triggered on btn click and shows form to edit profit and sets current profit data */
function editProfit(ID, description, category, value)
{
    document.getElementById("hiddenEditProfit").style.display = "block";

    document.getElementById("hiddenEditProfitID").setAttribute("value", ID);
    document.getElementById("hiddenEditProfitDescription").setAttribute("value", description);
    document.getElementById("hiddenEditProfitCategory").setAttribute("value", category);
    document.getElementById("hiddenEditProfitValue").setAttribute("value", value);
}

/* function that is triggered on btn click and hides form to edit profit */
function editProfitCancel()
{
    document.getElementById("hiddenEditProfit").style.display = "none";
}

/* function that is triggered on btn click and shows form to edit spending and sets current spending data */
function editSpending(ID, description, category, value)
{
    document.getElementById("hiddenEditSpending").style.display = "block";

    document.getElementById("hiddenEditSpendingID").setAttribute("value", ID);
    document.getElementById("hiddenEditSpendingDescription").setAttribute("value", description);
    document.getElementById("hiddenEditSpendingCategory").setAttribute("value", category);
    document.getElementById("hiddenEditSpendingValue").setAttribute("value", value);
}

/* function that is triggered on btn click and hides form to edit spending */
function editSpendingCancel()
{
    document.getElementById("hiddenEditSpending").style.display = "none";
}