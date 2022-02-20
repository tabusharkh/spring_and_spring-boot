<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Create an account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">

        <form:form method="POST" action="${contextPath}/addDeal" modelAttribute="dealForm" class="form-signin">
            <h2 class="form-signin-heading">Adding New Deal</h2>
            <spring:bind path="fromCurrencyCode">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="fromCurrencyCode" class="form-control" placeholder="From Currency Code"
                                autofocus="true"></form:input>
                    <form:errors path="fromCurrencyCode"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="toCurrencyCode">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="toCurrencyCode" class="form-control" placeholder="To Currency Code"></form:input>
                    <form:errors path="toCurrencyCode"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="stamp">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="stamp" class="form-control"
                                placeholder="Deal Stamp"></form:input>
                    <form:errors path="stamp"></form:errors>
                </div>
            </spring:bind>
            
            <spring:bind path="amount">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="number" path="amount" class="form-control"
                                placeholder="Deal Amount"></form:input>
                    <form:errors path="amount"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>