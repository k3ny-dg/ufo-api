window.onload = async function (){
    await loadData();
    await addFormHandler();
}

async function loadData()
{
    let uri= "http://localhost:8080/ufos/all";
    let params = {
        method: "get",
        mode: "cors"
    }

    try {
        let response = await fetch(uri, params);
        let json = await response.json();

        if(!response.ok) {
            console.log(json.description);
            return;
        }
        showData(json);
    }

    catch (error){
        console.log(error);
    }
    //     .then(function (response) {
    //         console.log(response);
    //         return response.json();
    //     })
    //     .then(function (json){
    //     console.log(json);
    //     showData(json);
    // });
}

function showData(ufos)
{

    let table = document.getElementById("ufo-table");

    let rows = document.querySelector("tr");
    rows.remove();

    for (let i = 0; i < ufos.length; i++) {

        let ufo = ufos[i];

        let tr = document.createElement("tr");

        for (let j = 0; j <= 6; j++) {
            table.appendChild(tr);
        }

        let id_td = document.createElement("td");
        let shape_td = document.createElement("td");
        let description_td = document.createElement("td");
        let encounter_td = document.createElement("td");
        let edit_td = document.createElement("td");
        let del_td = document.createElement("td");

        id_td.className = "row" + ufo.id;
        shape_td.className = "row" + ufo.id;
        description_td.className = "row" + ufo.id;
        encounter_td.className = "row" + ufo.id;
        edit_td.className = "row" + ufo.id;
        del_td.className = "row" + ufo.id;

        edit_td.className = "non-input";
        del_td.className = "non-input";

        let editBtn = document.createElement('input');
        editBtn.type = "button";
        editBtn.value = "Update";
        //editBtn.onclick = handleUpdateRecord;
        editBtn.onclick = makeEditable;

        let delBtn = document.createElement('input');
        delBtn.type = "button";
        delBtn.value = "Delete";
        delBtn.onclick = handleDeleteRecordSubmit;

        id_td.innerHTML = ufo.id;
        shape_td.innerHTML = ufo.shape;
        description_td.innerHTML = ufo.description;
        encounter_td.innerHTML = ufo.encounterLength;
        edit_td.appendChild(editBtn);
        del_td.appendChild(delBtn);

        tr.appendChild(id_td);
        tr.appendChild(shape_td);
        tr.appendChild(description_td);
        tr.appendChild(encounter_td);
        tr.appendChild(edit_td);
        tr.appendChild(del_td);
    }
}

async function addFormHandler()
{
    let formButton = document.getElementById("add-btn");
    formButton.onclick = handleFormSubmit;
}

async function handleFormSubmit(event)
{
    event.preventDefault();
    console.log("Handled form submit!");

    let table = document.getElementById("ufo-table");

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

    try
    {
        let response = await fetch(uri, params);

        if(!response.ok) {
            console.log(response.description);
            return;
        }
    await location.reload();

    }
    catch (error)
    {
        console.log(error);
    }

}

function makeEditable()
{

    let table = document.getElementById("ufo-table");
    let cells = table.getElementsByTagName("td");

    for (let i = 0; i < cells.length; i++) {
        cells[i].onclick = function () {

                let input = document.createElement("input");
                input.setAttribute('type', 'text');
                input.value = this.innerHTML;
                input.style.backgroundColor = "pink";

                this.innerHTML = '';
                this.append(input);
                this.firstElementChild.select();
            }
        }
}

async function handleUpdateRecord(event)
{

    event.preventDefault();
    console.log("Record updated!");

    let updatedRecord = {
        id: document.getElementById("ufo_id").value,
        shape: document.getElementById("shape").value,
        description: document.getElementById("description").value,
        encounterLength: document.getElementById("e-length").value
    };


    console.log(updatedRecord);

    let uri = "http://localhost:8080/ufos/update-ufo";
    let params = {
        method: "put",
        mode: "cors",
        body: JSON.stringify(updatedRecord),
        headers: {
            "Content-Type" : "application/json"
        }
    };

    try
    {
        let response = await fetch(uri, params);

        if(!response.ok) {
            console.log(response.description);
            //return;
        }
    }
    catch (error)
    {
        console.log(error);
    }

    // fetch(uri, params)
    //     .then(function (response){
    //         console.log(response)
    //     });
}


//http://localhost:8080/ufos/del/{ufoId}
async function handleDeleteRecordSubmit(event)
{
    event.preventDefault();
    console.log("Record deleted!")

    let del = event.target;
    let tr = del.parentElement.parentElement;
    let children = tr.children;
    let ufoId = children[0].innerHTML;

    let uri = "http://localhost:8080/ufos/del/"+ufoId;
    let params = {
        method: "delete",
        mode: "cors",
    }

    try
    {
        let response = await fetch(uri, params);

        if(!response.ok) {
            console.log(response.description);
            return;
        }

        await location.reload();
    }
    catch (error)
    {
        console.log(error);
    }


    fetch(uri, params)
        .then(function (response){
            console.log(response);
            return response.json();
        })
        .then(function (json){
            console.log(json);
        });
}

