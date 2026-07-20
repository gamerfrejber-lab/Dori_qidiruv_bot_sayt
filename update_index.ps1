$content = @'
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dori Qidiruv - Bosh sahifa</title>
    <style>
        :root {
            --bg-gradient: linear-gradient(135deg, #0a0a1a 0%, #0d1b3e 100%);
            --glass-bg: rgba(255, 255, 255, 0.08);
            --glass-border: rgba(255, 255, 255, 0.15);
            --btn-gradient: linear-gradient(90deg, #4fc3f7 0%, #7c4dff 100%);
            --text-color: #ffffff;
            --font-family: 'Inter', system-ui, -apple-system, sans-serif;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: var(--font-family);
        }

        body {
            background: var(--bg-gradient);
            background-attachment: fixed;
            color: var(--text-color);
            min-height: 100vh;
            overflow-x: hidden;
        }

        header {
            padding: 1rem 5%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            backdrop-filter: blur(20px);
            position: sticky;
            top: 0;
            z-index: 1000;
            border-bottom: 1px solid var(--glass-border);
            background: rgba(10, 10, 26, 0.5);
        }

        .logo {
            font-size: 1.5rem;
            font-weight: 800;
            background: var(--btn-gradient);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            text-decoration: none;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 0.95rem;
            transition: 0.3s;
            opacity: 0.8;
        }

        nav a:hover {
            opacity: 1;
            text-shadow: 0 0 10px rgba(255,255,255,0.5);
        }

        .header-right {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .lang-btn {
            background: var(--glass-bg);
            border: 1px solid var(--glass-border);
            color: white;
            padding: 6px 12px;
            border-radius: 10px;
            cursor: pointer;
            font-size: 0.8rem;
            transition: 0.3s;
        }

        .lang-btn:hover {
            background: rgba(255,255,255,0.15);
        }

        .login-btn {
            background: var(--btn-gradient);
            border: none;
            padding: 10px 24px;
            border-radius: 14px;
            color: white;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: 0.3s;
            display: inline-block;
        }

        .login-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(124, 77, 255, 0.4);
        }

        .hero {
            padding: 80px 5% 40px;
            text-align: center;
        }

        .hero h1 {
            font-size: clamp(2rem, 5vw, 3.5rem);
            margin-bottom: 25px;
            line-height: 1.2;
            animation: fadeIn 1s ease-out;
        }

        .search-container {
            max-width: 700px;
            margin: 0 auto 50px;
        }

        .search-box-wrapper {
            position: relative;
            margin-bottom: 20px;
        }

        .search-box {
            width: 100%;
            padding: 22px 30px;
            border-radius: 24px;
            border: 1px solid var(--glass-border);
            background: var(--glass-bg);
            backdrop-filter: blur(25px);
            color: white;
            font-size: 1.1rem;
            outline: none;
            transition: 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        }

        .search-box:focus {
            border-color: #4fc3f7;
            background: rgba(255,255,255,0.12);
            box-shadow: 0 0 30px rgba(79, 195, 247, 0.2);
            transform: scale(1.02);
        }

        .tags {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 12px;
        }

        .tag {
            background: var(--glass-bg);
            padding: 10px 20px;
            border-radius: 50px;
            font-size: 0.9rem;
            cursor: pointer;
            border: 1px solid var(--glass-border);
            transition: 0.3s;
            backdrop-filter: blur(10px);
        }

        .tag:hover {
            background: var(--btn-gradient);
            border-color: transparent;
            transform: translateY(-3px);
        }

        .section-title {
            margin: 60px 5% 30px;
            font-size: 2rem;
            font-weight: 700;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 30px;
            padding: 0 5% 60px;
        }

        .card {
            background: var(--glass-bg);
            backdrop-filter: blur(30px);
            border: 1px solid var(--glass-border);
            border-radius: 28px;
            padding: 30px;
            transition: 0.4s;
            animation: slideUp 0.6s ease-out both;
        }

        .card:hover {
            transform: translateY(-12px) scale(1.02);
            background: rgba(255,255,255,0.12);
            border-color: rgba(255,255,255,0.3);
        }

        .card-icon {
            font-size: 2.5rem;
            margin-bottom: 20px;
            display: block;
        }

        .card h3 {
            font-size: 1.4rem;
            margin-bottom: 12px;
            color: #4fc3f7;
        }

        .card p {
            font-size: 0.95rem;
            opacity: 0.7;
            margin-bottom: 20px;
            line-height: 1.5;
        }

        .price {
            font-size: 1.4rem;
            font-weight: 800;
            margin-bottom: 20px;
            display: block;
            color: #fff;
        }

        .stats {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            padding: 80px 5%;
            background: rgba(0,0,0,0.3);
            margin: 80px 0;
            backdrop-filter: blur(10px);
        }

        .stat-item {
            text-align: center;
        }

        .stat-item h2 {
            font-size: clamp(2rem, 4vw, 3.5rem);
            background: var(--btn-gradient);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            margin-bottom: 10px;
        }

        .stat-item p {
            font-size: 1.1rem;
            opacity: 0.6;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        @keyframes slideUp {
            from { opacity: 0; transform: translateY(40px); }
            to { opacity: 1; transform: translateY(0); }
        }

        @media (max-width: 768px) {
            header { padding: 1rem 3%; }
            nav { display: none; }
            .stats { grid-template-columns: 1fr; gap: 40px; }
            .grid { grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>

<header>
    <a href="index.html" class="logo">💊 Dori Qidiruv</a>
    <nav>
        <a href="index.html">Bosh sahifa</a>
        <a href="dorixonalar.html">Dorixonalar</a>
    </nav>
    <div class="header-right">
        <button class="lang-btn">UZ</button>
        <button class="lang-btn">RU</button>
        <a href="login.html" class="login-btn" id="profileBtn">Kirish</a>
    </div>
</header>

<section class="hero">
    <h1>Assalomu alaykum! <br> Dori topish endi oson</h1>
    <div class="search-container">
        <div class="search-box-wrapper">
            <input type="text" class="search-box" id="searchInput" placeholder="Dori nomini kiriting... (masalan: Paracetamol)">
        </div>
        <div class="tags">
            <span class="tag" onclick="quickSearch('Paracetamol')">💊 Paracetamol</span>
            <span class="tag" onclick="quickSearch('Ibuprofen')">🤒 Ibuprofen</span>
            <span class="tag" onclick="quickSearch('Amoxicillin')">🧬 Amoxicillin</span>
            <span class="tag" onclick="quickSearch('Aspirin')">❤️ Aspirin</span>
            <span class="tag" onclick="quickSearch('Vitamin C')">🍊 Vitamin C</span>
        </div>
    </div>
</section>

<div id="resultsSection" style="display:none;">
    <h2 class="section-title">Qidiruv natijalari</h2>
    <div class="grid" id="resultsGrid"></div>
</div>

<h2 class="section-title">Mashhur dorilar</h2>
<div class="grid" id="popularGrid">
    <div class="card">
        <span class="card-icon">💊</span>
        <h3>Arbidol 200mg</h3>
        <p>Virusli infektsiyalarni davolash va oldini olish uchun keng qo'llaniladigan vosita.</p>
        <span class="price">45 000 so'm</span>
        <button class="login-btn" style="width:100%">Sotib olish</button>
    </div>
    <div class="card" style="animation-delay: 0.1s">
        <span class="card-icon">🧪</span>
        <h3>Magniy B6</h3>
        <p>Asab tizimi faoliyatini normallashtiradi, charchoqni kamaytiradi va uyquni yaxshilaydi.</p>
        <span class="price">32 000 so'm</span>
        <button class="login-btn" style="width:100%">Sotib olish</button>
    </div>
    <div class="card" style="animation-delay: 0.2s">
        <span class="card-icon">🍃</span>
        <h3>Mezim Forte</h3>
        <p>Ovqat hazm qilish jarayonini yaxshilovchi fermentativ dori vositasi.</p>
        <span class="price">18 000 so'm</span>
        <button class="login-btn" style="width:100%">Sotib olish</button>
    </div>
</div>

<section class="stats">
    <div class="stat-item">
        <h2>500+</h2>
        <p>Dori turlari</p>
    </div>
    <div class="stat-item">
        <h2>120+</h2>
        <p>Dorixonalar</p>
    </div>
    <div class="stat-item">
        <h2>24/7</h2>
        <p>Xizmat ko'rsatish</p>
    </div>
</section>

<h2 class="section-title">Yaqin dorixonalar</h2>
<div class="grid" id="pharmacyGrid"></div>

<script>
    const searchInput = document.getElementById('searchInput');
    const resultsGrid = document.getElementById('resultsGrid');
    const resultsSection = document.getElementById('resultsSection');
    const pharmacyGrid = document.getElementById('pharmacyGrid');

    // Profile sync
    const userName = localStorage.getItem('user_name');
    if(userName) {
        const profileBtn = document.getElementById('profileBtn');
        profileBtn.innerText = "Profil: " + userName;
        profileBtn.href = 'profil.html';
    }

    function quickSearch(val) {
        searchInput.value = val;
        searchDori(val);
    }

    searchInput.addEventListener('input', (e) => {
        const val = e.target.value;
        if(val.length > 2) {
            searchDori(val);
        } else {
            resultsSection.style.display = 'none';
        }
    });

    async function searchDori(query) {
        try {
            const res = await fetch(`/api/dori/qidirish?nomi=${encodeURIComponent(query)}`);
            if(!res.ok) throw new Error();
            const data = await res.json();
            displayResults(data);
        } catch (e) {
            const mock = [
                {nomi: query, tavsif: "Ushbu dori barcha filiallarimizda mavjud.", narxi: "25 500 so'm"},
                {nomi: query + " Max", tavsif: "Kuchaytirilgan ta'sirga ega yangi dori.", narxi: "42 000 so'm"}
            ];
            displayResults(mock);
        }
    }

    function displayResults(data) {
        resultsSection.style.display = 'block';
        resultsGrid.innerHTML = data.map((item, i) => `
            <div class="card" style="animation-delay: ${i*0.1}s">
                <span class="card-icon">🔍</span>
                <h3>${item.nomi || item.name}</h3>
                <p>${item.tavsif || item.description || 'Ma\'lumot mavjud emas'}</p>
                <span class="price">${item.narxi || item.price || 'Narxi kelishiladi'}</span>
                <button class="login-btn" style="width:100%">Manzilni ko'rish</button>
            </div>
        `).join('');
    }

    async function loadPharmacies() {
        try {
            const res = await fetch('/api/dorixona/barchasi');
            if(!res.ok) throw new Error();
            const data = await res.json();
            displayPharmacies(data.slice(0, 3));
        } catch (e) {
            const mockPh = [
                {nom: "Arzon Pharm", manzil: "Chilonzor 2-kvartal", tel: "+998 71 123 45 67"},
                {nom: "Grand Bio", manzil: "Yunusobod 4-mavze", tel: "+998 90 987 65 43"},
                {nom: "Salomatlik 24", manzil: "Mirzo Ulug'bek ko'chasi", tel: "+998 71 555 00 11"}
            ];
            displayPharmacies(mockPh);
        }
    }

    function displayPharmacies(data) {
        pharmacyGrid.innerHTML = data.map((ph, i) => `
            <div class="card" style="animation-delay: ${i*0.1}s">
                <span class="card-icon">🏪</span>
                <h3>${ph.nom || ph.name}</h3>
                <p>📍 ${ph.manzil || ph.address}</p>
                <p>📞 ${ph.tel || ph.phone}</p>
                <button class="login-btn" style="width:100%">Xaritaga o'tish</button>
            </div>
        `).join('');
    }

    loadPharmacies();
</script>

</body>
</html>
'@
$content | Out-File -FilePath src\main\resources\static\index.html -Encoding utf8
