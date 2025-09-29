<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>ToDo List</title>
    <link rel="stylesheet" href="resources/CSS/main-page.css"/>
</head>
<body>
    <!--<h1>Здесь когда-нибудь будет ToDo List</h1>-->
    <div class="page-wrapper">
        <div class="container">
            <div class="header-container">
                <div class="header__text">
                    <h2>ToDo List</h2>
                </div>
                <div class="header__statistics">
                    <span>${numberOfActiveTasks} more to do, ${numberOfDoneTasks} done</span>
                </div>
            </div>
            <div class="filter-container">
                <form class="filter-form" action="/home" method="get">
                    <div class="filter-form__input">
                        <input type="radio" id="filter-form__status_all" name="filterParam" value="all" checked ${empty param.filterParam or (fn:toLowerCase(param.filterParam) != 'done' and fn:toLowerCase(param.filterParam) != 'active') ? 'checked' : ''}>
                        <label for="filter-form__status_all">All</label>
                    </div>
                    <div class="filter-form__input">
                        <input type="radio" id="filter-form__status_active" name="filterParam" value="active" ${fn:toLowerCase(param.filterParam) == 'active' ? 'checked' : ''}>
                        <label for="filter-form__status_active">Active</label>
                    </div>
                    <div class="filter-form__input">
                        <input type="radio" id="filter-form__status_done" name="filterParam" value="done" ${fn:toLowerCase(param.filterParam) == 'done' ? 'checked' : ''}>
                        <label for="filter-form__status_done">Done</label>
                    </div>
                    <button type="submit">Apply</button>
                </form>
            </div>
            <div class="tasks-container">
                <c:choose>
                    <c:when test="${not empty tasks}">
                        <c:forEach items="${tasks}" var="task">
                            <div class="task">
                                <div class="task__title"><span class="${task.state == 'DONE' ? "task__title_strikethrough" : ""}">${task.name}</span></div>
                                <div class="task__controls">
                                    <c:if test="${task.state != 'DONE'}">
                                        <form class="task__controls-form" action="/finish-task" method="post">
                                            <button type="submit" class="button_type_approve">
                                                <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <g clip-path="url(#clip0_258_5036)">
                                                        <path d="M8.5 0C3.808 0 0 3.808 0 8.5C0 13.192 3.808 17 8.5 17C13.192 17 17 13.192 17 8.5C17 3.808 13.192 0 8.5 0ZM7.65 12.257L3.4 8.007L4.5985 6.8085L7.65 9.8515L12.4015 5.1L13.6 6.307L7.65 12.257Z" fill="#fff"></path>
                                                    </g>
                                                    <defs>
                                                        <clipPath id="clip0_258_5036">
                                                            <rect width="17" height="17" fill="white"></rect>
                                                        </clipPath>
                                                    </defs>
                                                </svg>
                                            </button>
                                            <input type="hidden" name="id" value="${task.id}"/>
                                            <input type="hidden" name="filterParam" value="${param.filterParam}"/>
                                        </form>
                                    </c:if>
                                    <form class="task__controls-form" action="/delete-task" method="post">
                                        <button type="submit" class="button_type_close">
                                            <svg width="24" height="24" viewBox="0 0 24 24">
                                                <path d="M12.071 13.485l-2.828 2.829-1.415-1.415 2.829-2.828-2.829-2.828 1.415-1.415 2.828 2.829L14.9 7.828l.707.708.707.707-2.829 2.828 2.829 2.829-1.415 1.414-2.828-2.829z" fill="#000"></path>
                                            </svg>
                                        </button>
                                        <input type="hidden" name="id" value="${task.id}"/>
                                        <input type="hidden" name="filterParam" value="${param.filterParam}"/>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>

                    <c:when test="${fn:toLowerCase(param.filterParam) == 'done'}">
                        <div class="task">There are no DONE tasks!</div>
                    </c:when>
                    <c:when test="${fn:toLowerCase(param.filterParam) == 'active'}">
                        <div class="task">There are no ACTIVE tasks! You can add one</div>
                    </c:when>
                    <c:otherwise>
                        <div class="task">There are no any tasks! You can add one</div>
                    </c:otherwise>

                </c:choose>
            </div>
            <div class="management-container">
                <form class="management-form" action="/add-task" method="post">
                    <input type="text" name="name" placeholder="Write it down" class="management-form__input">
                    <button type="submit" class="management-form__button">Add task</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
