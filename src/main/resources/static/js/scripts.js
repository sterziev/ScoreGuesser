$(function() {
    $('#rounds').change(function getElementsById() {
            var id = $(this).val();
            var divElements = document.getElementsByTagName('div');
            for (index = 0; index < divElements.length; index++) {
                var elementId = divElements[index].id;
                if (elementId.lastIndexOf('round', 0) === 0) {

                    var x = document.getElementById(elementId);
                    if (elementId.lastIndexOf(id, 0) === 0) {
                        x.style.display = "block";
                    } else {
                        x.style.display = "none";
                    }
                }
            }
            var r = id.replace("round", "");
            document.getElementById("heading").innerHTML = "Round "+ r;
        }
    );
});