$(document).ready(function() {
  /*  $('#validar').on('click', function(e) {
      e.preventDefault();
      //  consumirServicio();
    });
  */
  $("#frm").submit(function(e) {
    e.preventDefault();
    var url = $(this).attr("action");
    var type = $(this).attr("method");
    var data = new FormData($(this)[0]);
    consumirServlet(url, type, data);
  });
});

function consumirServicio() {
  var nombre = $('input[name=usuario]').val();
  var password = $('input[name=password]').val();

  var usuario = {
    "usuario": nombre,
    "password": password
  };

  var data = JSON.stringify(usuario);


  $.post({
    headers: {
      //No olvidar las cabeceras
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    data: data,
    //datos que se envian a traves de ajax
    url: 'http://localhost:8080/RestJR/services/Home/validarUsuario', //archivo que recibe la peticion
    type: 'POST', //método de envio
    success: function(response, status) { //una vez que el archivo recibe el request lo procesa y lo devuelve
      var codigo = status;
      console.log(response);
      if (codigo === 'success') {
        if (response.userValido) {
          $('#respuesta').addClass('badge-success').removeAttr('hidden').text("Usuario Correcto");
        } else {
          $('#respuesta').addClass('badge-danger').removeAttr('hidden').text("Usuario Erroneo");
        }
      }
    },
    dataType: 'json'
  });
}

function consumirServlet(url, type, data) {
  if (validarExtensiones()) {
    $.ajax({
      url: url,
      type: type,
      data: data,
      processData: false,
      contentType: false,
      success: function(response) {
        console.log(response);
      }
    });
  }
}


function validarExtensiones() {
  var fileInput = $('input[name=archivo]');
  var filePath = fileInput.val();
  var allowedExtensions = /(.jpg|.jpeg|.png|.gif|.pdf)$/i;
  if (!allowedExtensions.exec(filePath)) {
    alert("Solo archivos con extension .jpg|.jpeg|.png|.gif");
    fileInput.val("");
    return false;
  }
  return true;
}