class CalculateTipButton {
    constructor(root) {
        this.root = root;
        this.init();
    }

    init () {
        // attach click event to  button
        let calculateTipButton = document.getElementById('calculate_tip_button');
        if (calculateTipButton) {
            calculateTipButton.addEventListener("click", () => {
                this.calculateTip();
            });
        }
    }

    destroy () {
        let calculateTipButton = document.getElementById('calculate_tip_button');
        if (calculateTipButton) {
            calculateTipButton.removeEventListener("click", () => {
                this.calculateTip();
            });
        }
    }

    calculateTip () {
        var bill = Number(document.getElementById('bill').value);
        var tip = Number((document.getElementById('tip').value) * 0.01);

        var tip_result = bill * tip;

        var total_result = bill + tip_result;

        document.getElementById("tip_result").innerHTML = tip_result;

        document.getElementById("total_result").innerHTML = total_result;
    }
}


// DOMContentLoaded event will allow us to run the following function when
// everything is ready. Think of the following code will only be executed by
// the end of document
document.addEventListener('DOMContentLoaded', () => {
    // use querySelector to find the calculate tip button
    let calculateTipButton = document.querySelectorAll('#calculate_tip_button');
    
    if (calculateTipButton) {
        new CalculateTipButton(calculateTipButton);
    }
});