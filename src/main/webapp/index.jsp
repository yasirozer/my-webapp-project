<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TODO App</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
            color: #e0e0e0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
            padding: 20px;
        }

        .container-custom {
            max-width: 600px;
            margin: 0 auto;
        }

        .header {
            text-align: center;
            margin-bottom: 40px;
            animation: fadeIn 0.6s ease-in;
        }

        .header h1 {
            font-size: 3rem;
            font-weight: 700;
            background: linear-gradient(135deg, #00d4ff 0%, #0099ff 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            margin-bottom: 10px;
        }

        .header p {
            color: #a0a0a0;
            font-size: 1.1rem;
        }

        .input-group {
            margin-bottom: 30px;
            gap: 10px;
        }

        .input-group input {
            background-color: #2a2a3e !important;
            border-color: #404050 !important;
            color: #e0e0e0 !important;
            padding: 12px 15px;
            border-radius: 8px;
            font-size: 1rem;
            transition: all 0.3s ease;
        }

        .input-group input:focus {
            background-color: #333340 !important;
            border-color: #00d4ff !important;
            box-shadow: 0 0 10px rgba(0, 212, 255, 0.2);
            color: #e0e0e0 !important;
        }

        .input-group input::placeholder {
            color: #707080 !important;
        }

        .btn-add {
            background: linear-gradient(135deg, #00d4ff 0%, #0099ff 100%);
            border: none;
            color: white;
            font-weight: 600;
            padding: 12px 30px;
            border-radius: 8px;
            transition: all 0.3s ease;
            cursor: pointer;
        }

        .btn-add:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(0, 212, 255, 0.4);
        }

        .btn-add:active {
            transform: translateY(0);
        }

        .task-list {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .task-item {
            background-color: #2a2a3e;
            border: 1px solid #404050;
            padding: 15px;
            border-radius: 8px;
            display: flex;
            align-items: center;
            gap: 12px;
            transition: all 0.3s ease;
            animation: slideIn 0.3s ease;
        }

        .task-item:hover {
            border-color: #00d4ff;
            box-shadow: 0 4px 15px rgba(0, 212, 255, 0.1);
            background-color: #313145;
        }

        .task-checkbox {
            width: 20px;
            height: 20px;
            cursor: pointer;
            accent-color: #00d4ff;
        }

        .task-title {
            flex: 1;
            font-size: 1rem;
            cursor: pointer;
            padding: 5px 8px;
            border-radius: 4px;
            transition: all 0.2s ease;
            word-break: break-word;
        }

        .task-title:hover:not(.editing) {
            background-color: rgba(0, 212, 255, 0.1);
            color: #00d4ff;
        }

        .task-title.completed {
            text-decoration: line-through;
            color: #707080;
        }

        .task-title.editing {
            background-color: rgba(0, 212, 255, 0.2);
            padding: 5px;
        }

        .task-input {
            background-color: #1a1a2e !important;
            border: 2px solid #00d4ff !important;
            color: #e0e0e0 !important;
            padding: 5px 8px;
            border-radius: 4px;
            font-size: 1rem;
        }

        .task-input:focus {
            box-shadow: 0 0 10px rgba(0, 212, 255, 0.3);
            color: #e0e0e0 !important;
        }

        .task-actions {
            display: flex;
            gap: 8px;
        }

        .btn-icon {
            background: none;
            border: none;
            color: #a0a0a0;
            cursor: pointer;
            font-size: 1.2rem;
            padding: 5px;
            border-radius: 4px;
            transition: all 0.2s ease;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .btn-icon:hover {
            color: #00d4ff;
            background-color: rgba(0, 212, 255, 0.1);
        }

        .btn-icon.delete:hover {
            color: #ff6b6b;
            background-color: rgba(255, 107, 107, 0.1);
        }

        .empty-state {
            text-align: center;
            padding: 40px 20px;
            color: #707080;
            animation: fadeIn 0.6s ease-in;
        }

        .empty-state i {
            font-size: 3rem;
            margin-bottom: 15px;
            opacity: 0.5;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateX(-10px);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }

        @media (max-width: 768px) {
            .header h1 {
                font-size: 2rem;
            }

            .task-item {
                flex-wrap: wrap;
            }

            .task-title {
                flex: 1 1 100%;
                order: 2;
            }

            .task-checkbox {
                order: 1;
            }

            .task-actions {
                order: 3;
                flex: 1 1 100%;
                margin-left: 32px;
                margin-top: 8px;
            }
        }
    </style>
</head>
<body>
    <div class="container-custom">
        <div class="header">
            <h1><i class="bi bi-check2-square"></i> TODO</h1>
            <p>Stay organized and productive</p>
        </div>

        <div class="input-group">
            <input 
                type="text" 
                id="task-input" 
                placeholder="Add a new task..." 
                class="form-control"
                autocomplete="off"
            >
            <button class="btn-add" onclick="addTask()">
                <i class="bi bi-plus-lg"></i> Add
            </button>
        </div>

        <div class="task-list" id="task-list"></div>
        
        <div class="empty-state" id="empty-state" style="display: none;">
            <i class="bi bi-inbox"></i>
            <p>No tasks yet. Create one to get started!</p>
        </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const API_BASE = '/my-webapp-project/api/tasks';

        // Fetch all tasks from the backend
        async function fetchTasks() {
            try {
                const response = await fetch(API_BASE);
                const tasks = await response.json();
                renderTasks(tasks);
            } catch (error) {
                console.error('Error fetching tasks:', error);
            }
        }

        // Render tasks to the DOM
        function renderTasks(tasks) {
            const taskList = document.getElementById('task-list');
            const emptyState = document.getElementById('empty-state');
            
            taskList.innerHTML = '';

            if (tasks.length === 0) {
                emptyState.style.display = 'block';
                return;
            }

            emptyState.style.display = 'none';

            tasks.forEach(task => {
                const taskItem = document.createElement('div');
                taskItem.className = 'task-item';
                taskItem.id = `task-${task.id}`;

                taskItem.innerHTML = `
                    <input 
                        type="checkbox" 
                        class="task-checkbox" 
                        ${task.completed ? 'checked' : ''}
                        onchange="toggleComplete(${task.id}, this.checked)"
                    >
                    <span 
                        class="task-title ${task.completed ? 'completed' : ''}" 
                        ondblclick="startEditTask(${task.id})"
                        data-task-id="${task.id}"
                    >
                        ${escapeHtml(task.title)}
                    </span>
                    <div class="task-actions">
                        <button class="btn-icon" onclick="startEditTask(${task.id})" title="Edit">
                            <i class="bi bi-pencil"></i>
                        </button>
                        <button class="btn-icon delete" onclick="deleteTask(${task.id})" title="Delete">
                            <i class="bi bi-trash"></i>
                        </button>
                    </div>
                `;

                taskList.appendChild(taskItem);
            });
        }

        // Add a new task
        async function addTask() {
            const taskInput = document.getElementById('task-input');
            const title = taskInput.value.trim();

            if (!title) {
                taskInput.focus();
                return;
            }

            try {
                const response = await fetch(API_BASE, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: title,
                        completed: false
                    })
                });

                if (response.ok) {
                    taskInput.value = '';
                    taskInput.focus();
                    fetchTasks();
                }
            } catch (error) {
                console.error('Error adding task:', error);
            }
        }

        // Toggle task completion status
        async function toggleComplete(id, completed) {
            try {
                const tasks = await fetch(API_BASE).then(r => r.json());
                const task = tasks.find(t => t.id === id);

                if (task) {
                    const response = await fetch(API_BASE, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            id: id,
                            title: task.title,
                            completed: completed
                        })
                    });

                    if (response.ok) {
                        fetchTasks();
                    }
                }
            } catch (error) {
                console.error('Error toggling task:', error);
            }
        }

        // Start inline editing
        function startEditTask(id) {
            const titleElement = document.querySelector(`[data-task-id="${id}"]`);
            const currentText = titleElement.textContent;

            if (titleElement.classList.contains('editing')) {
                return;
            }

            titleElement.classList.add('editing');
            const input = document.createElement('input');
            input.type = 'text';
            input.className = 'task-input';
            input.value = currentText;
            input.maxLength = 255;

            titleElement.textContent = '';
            titleElement.appendChild(input);
            input.focus();
            input.select();

            function saveEdit() {
                const newTitle = input.value.trim();
                if (newTitle && newTitle !== currentText) {
                    updateTaskTitle(id, newTitle);
                } else {
                    titleElement.classList.remove('editing');
                    titleElement.textContent = currentText;
                }
            }

            input.addEventListener('blur', saveEdit);
            input.addEventListener('keypress', (e) => {
                if (e.key === 'Enter') {
                    saveEdit();
                }
            });
            input.addEventListener('keydown', (e) => {
                if (e.key === 'Escape') {
                    titleElement.classList.remove('editing');
                    titleElement.textContent = currentText;
                }
            });
        }

        // Update task title
        async function updateTaskTitle(id, newTitle) {
            try {
                const tasks = await fetch(API_BASE).then(r => r.json());
                const task = tasks.find(t => t.id === id);

                if (task) {
                    const response = await fetch(API_BASE, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            id: id,
                            title: newTitle,
                            completed: task.completed
                        })
                    });

                    if (response.ok) {
                        fetchTasks();
                    }
                }
            } catch (error) {
                console.error('Error updating task:', error);
                fetchTasks();
            }
        }

        // Delete a task
        async function deleteTask(id) {
            try {
                const response = await fetch(`${API_BASE}/${id}`, {
                    method: 'DELETE'
                });

                if (response.ok) {
                    fetchTasks();
                }
            } catch (error) {
                console.error('Error deleting task:', error);
            }
        }

        // Escape HTML to prevent XSS
        function escapeHtml(text) {
            const div = document.createElement('div');
            div.textContent = text;
            return div.innerHTML;
        }

        // Allow Enter key to add task
        document.addEventListener('DOMContentLoaded', () => {
            fetchTasks();
            document.getElementById('task-input').addEventListener('keypress', (e) => {
                if (e.key === 'Enter') {
                    addTask();
                }
            });
        });
    </script>
</body>
</html>
