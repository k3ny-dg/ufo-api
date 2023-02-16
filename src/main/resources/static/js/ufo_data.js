window.onload = function (){
    loadData();
    addFormHandler();
}

function loadData()
{
    let uri= "http://localhost:8080/ufos/all";
    let params = {
        method: "get",
        mode: "cors"
    }
    fetch(uri, params)
        .then(function (response) {
            console.log(response);
            return response.json();
        })
        .then(function (json){
        console.log(json);
        showData(json);
    });
}

function showData(ufos)
{

    let table = document.getElementById("ufo-table");

    for (let i = 0; i < ufos.length; i++) {

        let ufo = ufos[i];

        let tr = document.createElement("tr");
        table.appendChild(tr);
        table.appendChild(tr);
        table.appendChild(tr);
        table.appendChild(tr);
        table.appendChild(tr);
        table.appendChild(tr);

        let id_td = document.createElement("td");
        let shape_td = document.createElement("td");
        let description_td = document.createElement("td");
        let encounter_td = document.createElement("td");
        let edit_td = document.createElement("td");
        let del_td = document.createElement("td");

        id_td.className = "id";
        shape_td.className = "shape";
        description_td.className = "description";
        encounter_td.className = "encounter";
        edit_td.className = "edit";
        del_td.className = "del";

        id_td.innerHTML = ufo.id;
        shape_td.innerHTML = ufo.shape;
        description_td.innerHTML = ufo.description;
        encounter_td.innerHTML = ufo.encounterLength;
        edit_td.innerHTML = "Edit";
        del_td.innerHTML = "Delete";

        console.log(ufo.id);
        console.log(ufo.shape);
        console.log(ufo.description);
        console.log(ufo.encounterLength);


        tr.appendChild(id_td);
        tr.appendChild(shape_td);
        tr.appendChild(description_td);
        tr.appendChild(encounter_td);
        tr.appendChild(edit_td);
        tr.appendChild(del_td);
    }
}

function addFormHandler()
{
    let formButton = document.getElementById("add-btn");
    formButton.onclick = handleFormSubmit;
}

function handleFormSubmit(event)
{
    event.preventDefault();
    console.log("Handled form submit!");

    let newRecord = {
        id: document.getElementById("ufo_id").value,
        shape: document.getElementById("shape").value,
        description: document.getElementById("description").value,
        encounterLength: document.getElementById("e-length").value
    };

    let uri = "http://localhost:8080/ufos/add-ufo";
    let params = {
        method: "post",
        mode: "cors",
        body: JSON.stringify(newRecord),
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(uri,params)
        .then(function (response){
            console.log(response)
        });
}
