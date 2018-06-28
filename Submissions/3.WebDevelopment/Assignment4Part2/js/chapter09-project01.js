// this function is going to get called every time the focus or blur events are triggered
// in one of our form's input element
function setBackground(e) {
      if(e.type == "focus") {
            e.target.className += ' highlight';
      }
      else if (e.type == "blur") {
            e.target.className = e.target.className.replace(' highlight', '');
            e.classList.remove("error");
      }
}


// set up the event listeners only after the DOM is loaded
window.addEventListener("load", function() {
      var fields = document.getElementsByClassName("hilightable");
      for (var i = 0; i < fields.length; i++) {
            fields[i].addEventListener("focus",setBackground);
            fields[i].addEventListener("blur",setBackground);
      }
});


// add the appropriate handler for these required controls that will remove the CSS class error
// that have changed content
window.addEventListener("change", function(){
      var requiredFields = document.getElementsByClassName("required");
      for(var i =0; i<requiredFields.length;i++) {
            if(requiredFields[i].value == null || requiredFields[i].value == ""){
                  e.preventDefault();
                  requiredFields[i].classList.add("error");
            }
            else if (e.type == "blur"){
                  requiredFields[i].classList.remove("error");
            }
      }
});


// add the event handler for the submit event of the form.
// In this handler, if any  of the required form elements are empty,
// then add the CSS class error to any of the empty elements.
window.addEventListener("submit", function(e) {
      var requiredFields = document.getElementsByClassName("required");
      for (var i =0; i < requiredFields.length; i++) {
        if (requiredFields[i].value == null || requiredFields[i].value == ""){
            // e.preventDefault() prevents the default action from happening,
            // e.g. prevents a submit button from submitting the form.
            e.preventDefault();
            requiredFields[i].classList.add("error");
         }
         else {
              requiredFields[i].classList.remove("error");
         }
      }
});

// when the user presses the reset button
window.addEventListener("reset", function(e) {
      var requiredFields = document.getElementsByClassName("required");
      for (var i =0; i < requiredFields.length; i++) {
            requiredFields[i].classList.remove("error");
      }
});







