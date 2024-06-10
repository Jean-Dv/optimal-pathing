<%@ page contentType="text/html" language="java" %>
<script src="https://cdn.tailwindcss.com"></script>
<script>
    tailwind.config = {
        theme: {
      screens: {
        'sm': {max: "960px"},
      },
    }
  }
</script>
<style>
    #warning-message {
      display: none;
    }
</style>
<script>
    function showAlert() {
      document.getElementById('warning-message').style.display = 'flex'; 
    }

    function hideWarning() {
      document.getElementById('warning-message').style.display = 'none'; 
    }
</script>