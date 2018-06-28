/* define functions here */


// calculate total is the java script function which returns the product
// of quantity and price

function calculateTotal(quantity, price)
{
    return quantity * price;
}


// function to output the cart row

function outputCartRow(file, title, quantity, price, total)
{
    document.write("<tr>");
    document.write("<td>");
    document.write("<img src=images/"+file+">");
    document.write("</td>");

    document.write("<td>");
    document.write(title);
    document.write("</td>");

    document.write("<td>");
    document.write(quantity);
    document.write("</td>");

    document.write("<td>");
    document.write("$"+price.toFixed(2));
    document.write("</td>");

    document.write("<td>");
    document.write("$"+total.toFixed(2));
    document.write("</td>");

    document.write("<tr>");

}

// subtotal function

function calcSubTotal()
{
    var sub_total = 0;
    for (i = 0 ; i < 3; i++)
    {
        quantity =quantities[i];
        price = prices[i];
        total = calculateTotal(quantity, price);

        sub_total += total;
    }
    return sub_total.toFixed(2);
}


// calculate tax
function calcTax()
{
    var total_tax = calcSubTotal() * tax_rate;
    return total_tax.toFixed(2);
}

// calculate shipping
function calcShipping()
{
    var subtotal = calcSubTotal();
    var ship_amnt = 0;
    if (subtotal > 1000)
    {

        return ship_amnt.toFixed(2);
    }
    ship_amnt = 40;
    return ship_amnt.toFixed(2);
}

function calcGrandTotal()
{
    var subtotal = calcSubTotal();
    var tax = calcTax();
    var shipping = calcShipping();
    var grand_total = parseFloat(subtotal) + parseFloat(tax) + parseFloat(shipping);
    return grand_total.toFixed(2);
}