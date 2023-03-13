window.onload = async function () {
    await loadData();
    await addFormHandler();
    await addReportFormHandler();
    await loadImageOfDay();
}

// load in NASA's Astronomy Photo of the Day
async function loadImageOfDay() {

    let uri = "https://api.nasa.gov/planetary/apod?api_key=YbV2JwjaHfLqGxsJqThZMrWEWUlutAHIpdgjQSWX";
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
        await displayImage(json);
    } catch (error){
        console.log(error);
    }
}

// Load a given number of ISS Daily report from Space Flight News
async function loadSpaceReports(num) {

    let uri = "https://api.spaceflightnewsapi.net/v3/reports?_limit=" + num;
    let params = {
        method: "get",
        mode: "cors"
    }

    try {
        let response = await fetch(uri, params);
        let json = await response.json();

        if (!response.ok){
            console.log(json.description);
        }
        await showReports(json);
            } catch (error) {
        console.log(error);
    }
}

// load in data from our UFO API
async function loadData() {
    let uri = "http://34.71.224.198:8080/ufos/all";
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

// display the loaded in NASA astronomy image
async function displayImage(nasa) {

    let daily = document.getElementById("daily-img");

    let img = document.createElement("img");

    let imgSrc = nasa.url;
    img.setAttribute("src", imgSrc);
    img.setAttribute("id", "nasa-img");
    img.classList.add("daily");
    console.log(img);
    daily.appendChild(img);
}

// display the loaded in reports
async function showReports(reports) {

    clearReports();
    let spaceReports = document.getElementById("view-reports");

    for (let i = 0; i < reports.length; i++) {

        let report = reports[i];

        let title = document.createElement("h3");
        title.innerHTML = report.title;
        title.classList.add ("nasa-report");

        let urlDiv = document.createElement("div");
        urlDiv.classList.add("url-div");
        let url = document.createElement("a");
        url.innerHTML = report.url;
        url.setAttribute("href", report.url);
        url.setAttribute("target", "_blank")

        let repSummary = document.createElement("p");
        repSummary.innerHTML = report.summary;
        repSummary.classList.add ("nasa-report");

        spaceReports.appendChild(title);
        spaceReports.appendChild(repSummary);
        spaceReports.appendChild(urlDiv);
        urlDiv.appendChild(url);

        let hrBreak = document.createElement("hr");
        spaceReports.appendChild(hrBreak);
    }
}

// display the ufo data
function showData(ufos) {

    let table = document.getElementById("ufo-table");

    for (let i = 0; i < ufos.length; i++) {

        let ufo = ufos[i];

        let tr = document.createElement("tr");

        for (let j = 0; j <= 6; j++) {
            table.appendChild(tr);
        }

        // create tds to put in each table row
        let id_td = document.createElement("td");
        let shape_td = document.createElement("td");
        let description_td = document.createElement("td");
        let encounter_td = document.createElement("td");
        let edit_td = document.createElement("td");
        let del_td = document.createElement("td");


        // create ids for each individual object's data point
        id_td.id = "id" + ufo.id;
        shape_td.id = "shape" + ufo.id;
        description_td.id = "desc" + ufo.id;
        encounter_td.id = "encounter" + ufo.id;
        edit_td.id = "edit" + ufo.id;
        del_td.id = "del" + ufo.id;

        // add classes for each column's data
        id_td.className = 'ufoData';
        shape_td.className = 'ufoData';
        description_td.className = 'ufoData';
        encounter_td.className = 'ufoData';
        edit_td.className = 'edit';
        del_td.className = 'delete';

        // create an "edit" button that makes the fields editable
        let editBtn = document.createElement('input');
        editBtn.type = "button";
        editBtn.value = "Update";
        editBtn.className = 'update';
        editBtn.onclick = makeEditable;

        // create a delete button
        let delBtn = document.createElement('input');
        delBtn.type = "button";
        delBtn.value = "Delete";
        delBtn.onclick = handleDeleteRecordSubmit;
        delBtn.className = "del";

        // create a save button that updates the data
        let saveBtn = document.createElement('input');
        saveBtn.type = "button";
        saveBtn.value = "Save";
        saveBtn.onclick = handleUpdateRecord;
        saveBtn.className = 'save';

        // put the data + buttons into the tds
        id_td.innerHTML = ufo.id;
        shape_td.innerHTML = ufo.shape;
        description_td.innerHTML = ufo.description;
        encounter_td.innerHTML = ufo.encounterLength;
        edit_td.appendChild(editBtn);
        del_td.appendChild(delBtn);
        edit_td.appendChild(saveBtn);

        // append the completed tds to each row
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

async function addReportFormHandler() {
    let formButton = document.getElementById("report-btn");
    formButton.onclick = handleReportSubmit;
}

async function handleReportSubmit() {
    event.preventDefault();
    console.log("Gathering reports!")

    let numReports = document.getElementById("num").value;


    console.log("Number of reports: " + numReports);

    await loadSpaceReports(numReports);

}

async function handleFormSubmit(event) {
    event.preventDefault();
    console.log("Handled form submit!");

    // grab the new record from the form
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


// jQuery traversal reference: https://learn.jquery.com/using-jquery-core/traversing/
// jQuery .click() https://api.jquery.com/click/
function makeEditable() {

    // when an update button is clicked
    $(document).on('click', '.update', function () {

        // select its parent's (the td that holds the button) siblings  (tds with the data only)
        $(this).parents().siblings('td.ufoData').each(function () {
            let cellContent = $(this).html();

            // and add an input to each td
            // and assign a unique value that it can be identified with
            $(this).html('<input id="' + $(this).attr('id') + ' _up"' + ' value="' + cellContent + '" />');
        });

        let del = $(this).parent().siblings('td.delete').attr("id");

        // hide the delete button
        document.getElementById(del).style.display = "none";

        // hide the update button
        $(this).hide();
        $(this).children('input.update').hide();
    });
}

async function handleUpdateRecord(event) {

    event.preventDefault();
    console.log("Record updated!");

    // put all the data from the tds into an array
    let childArray = $(this).parent().siblings().children().toArray();

    // assign the values in the array to variables
    let ufoId = childArray[0].value;
    let ufoShape = childArray[1].value;
    let ufoDesc = childArray[2].value;
    let ufoEncounter = childArray[3].value;


    console.log("UFO: " + ufoId);
    console.log("Shape: " + ufoShape);
    console.log("Desc: " + ufoDesc);
    console.log("Encounter: " + ufoEncounter);

    // place those values into the update request
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

    let rows = $(this).parent().siblings();
    console.log(rows);
    let ufoId = rows[0].innerHTML;

    console.log(ufoId);
    // delete the requested record
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

function clearReports(){

    let container = document.getElementById("view-reports");

    while (container.firstChild) {
       container.firstChild.remove()
    }

}