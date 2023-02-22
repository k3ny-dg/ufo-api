window.onload = async function () {
    await loadData();
    await addFormHandler();
}

async function loadData() {
    let uri = "http://localhost:8080/ufos/all";
    let params = {
        method: "get",
        mode: "cors"
    }

    try {
        let response = await fetch(uri, params);
        let json = await response.json();

        if (!response.ok) {
            console.log(json.description);
            return;
        }
        showData(json);
    } catch (error) {
        console.log(error);
    }
}

function showData(ufos) {

    let table = document.getElementById("ufo-table");

    let rows = document.querySelector("tr");

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

        id_td.id = "id" + ufo.id;
        shape_td.id = "shape" + ufo.id;
        description_td.id = "desc" + ufo.id;
        encounter_td.id = "encounter" + ufo.id;
        edit_td.id = "edit" + ufo.id;
        del_td.id = "del" + ufo.id;

        id_td.className = 'data';
        shape_td.className = 'data';
        description_td.className = 'data';
        encounter_td.className = 'data';
        edit_td.className = 'edit';
        del_td.className = 'delete';


        let editBtn = document.createElement('input');
        editBtn.type = "button";
        editBtn.value = "Update";
        editBtn.className = 'update';
        editBtn.onclick = makeEditable;

        let delBtn = document.createElement('input');
        delBtn.type = "button";
        delBtn.value = "Delete";
        delBtn.onclick = handleDeleteRecordSubmit;
        delBtn.className = "del";

        let saveBtn = document.createElement('input');
        saveBtn.type = "button";
        saveBtn.value = "Save";
        saveBtn.onclick = handleUpdateRecord;
        saveBtn.className = 'save';

        id_td.innerHTML = ufo.id;
        shape_td.innerHTML = ufo.shape;
        description_td.innerHTML = ufo.description;
        encounter_td.innerHTML = ufo.encounterLength;
        edit_td.appendChild(editBtn);
        del_td.appendChild(delBtn);
        edit_td.appendChild(saveBtn);

        tr.appendChild(id_td);
        tr.appendChild(shape_td);
        tr.appendChild(description_td);
        tr.appendChild(encounter_td);
        tr.appendChild(edit_td);
        tr.appendChild(del_td);
    }
}

async function addFormHandler() {
    let formButton = document.getElementById("add-btn");
    formButton.onclick = handleFormSubmit;
}

async function handleFormSubmit(event) {
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

    try {
        let response = await fetch(uri, params);

        if (!response.ok) {
            console.log(response.description);
            return;
        }

        await $("#ufo-table tr").remove();
        await loadData();

    } catch (error) {
        console.log(error);
    }

}

function makeEditable() {

    $(document).on('click', '.update', function () {
        $(this).parents().siblings('td.data').each(function () {
            let cellContent = $(this).html();
            $(this).html('<input id="' + $(this).attr('id') + ' _up"' + ' value="' + cellContent + '" />');
        });

        let del = $(this).parent().siblings('td.delete').attr("id");

        document.getElementById(del).style.display = "none";
        $(this).hide();
        $(this).children('input.update').hide();
    });
}

async function handleUpdateRecord(event) {

    event.preventDefault();
    console.log("Record updated!");

    let childArray = $(this).parent().siblings().children().toArray();

    let ufoId = childArray[0].value;
    let ufoShape = childArray[1].value;
    let ufoDesc = childArray[2].value;
    let ufoEncounter = childArray[3].value;


    console.log("UFO: " + ufoId);
    console.log("Shape: " + ufoShape);
    console.log("Desc: " + ufoDesc);
    console.log("Encounter: " + ufoEncounter);


    let updatedRecord = {
        id: ufoId,
        shape: ufoShape,
        description: ufoDesc,
        encounterLength: ufoEncounter
    };


    console.log(updatedRecord);

    let uri = "http://localhost:8080/ufos/update-ufo";
    let params = {
        method: "put",
        mode: "cors",
        body: JSON.stringify(updatedRecord),
        headers: {
            "Content-Type": "application/json"
        }
    };

    try {
        let response = await fetch(uri, params);

        if (!response.ok) {
            console.log(response.description);
            //return;
        }

        await $("#ufo-table tr").remove();
        await loadData();

    } catch (error) {
        console.log(error);
    }
}


//http://localhost:8080/ufos/del/{ufoId}
async function handleDeleteRecordSubmit(event) {
    event.preventDefault();
    console.log("Record deleted!")

    let del = event.target;
    let tr = del.parentElement.parentElement;
    let children = tr.children;
    let ufoId = children[0].innerHTML;

    let uri = "http://localhost:8080/ufos/del/" + ufoId;
    let params = {
        method: "delete",
        mode: "cors",
    }

    try {
        let response = await fetch(uri, params);

        if (!response.ok) {
            console.log(response.description);
            return;
        }

        await $("#ufo-table tr").remove();
        await loadData();

    } catch (error) {
        console.log(error);
    }
}

