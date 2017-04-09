<%--@elvariable id="student" type="sh.model.Student"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student form</title>
</head>
<body>
<form class="student-form" action="student" method="post">
    <div>
        <label for="firstName">First name
            <input id="firstName" name="firstName" type="text" value="${student.firstName}">
        </label>
    </div>
    <div>
        <label for="secondName">Second name
            <input id="secondName" name="secondName" type="text" value="${student.secondName}">
        </label>
    </div>
    <div>
        <label for="avgMark">Average mark
            <input id="avgMark" name="avgMark" type="text" value="${student.avgMark}"></label>
    </div>
    <input name="id" value="${student.id}" hidden>
    <div>
        <label>Group number</label>
        <input type="text" name="groupNumber" value="${student.groupNumber}">
    </div>
    <div>
        <button type="submit">${action}</button>
    </div>
</form>

<div class="student-form controls">
    <a href="students">Back</a>

    <%--<form action="student" method="delete">--%>
        <%--<button>Delete</button>--%>
    <%--</form>--%>
</div>
</body>
</html>