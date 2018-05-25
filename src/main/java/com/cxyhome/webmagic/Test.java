import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.TextUtils;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public abstract class b extends a
{
    public static final String sCaller = "secret.wdj.client";
    protected static final String sCallerKey = "LVJd97AbRtikeYRRhi3ocdwSD";
    private static final Comparator<String> sComparator = new c();
    private Map<String, String> mABTestMap;
    protected JSONObject mExtraObject;
    j mRequestArgs;
    protected long mRequestId = generateRequestId();

    public b(j paramj, String paramString1, String paramString2)
    {
        super(paramj, paramString1, paramString2);
        this.mRequestArgs = paramj;
        paramj.a(this.mRequestId);
        this.mABTestMap = new HashMap();
    }

    private JSONObject generateClientJsonStr()
    {
        JSONObject localJSONObject = new JSONObject();
        Context localContext = PPApplication.q();
        localJSONObject.put("osVersion", Build.VERSION.SDK_INT);
        localJSONObject.put("ch", g.a(localContext));
        localJSONObject.put("cityCode", o.a());
        PPApplication.p();
        PPApplication.l();
        localJSONObject.put("productId", 2011);
        localJSONObject.put("brand", v.d());
        localJSONObject.put("udid", UDIDUtil.a(PPApplication.q()));
        localJSONObject.put("utdid", v.s());
        setClientExArgIfNeed(localJSONObject);
        return setClientArgs(localJSONObject, localContext);
    }

    private static long generateRequestId()
    {
        return UUID.randomUUID().getMostSignificantBits();
    }

    private String getAsString(Object paramObject)
    {
        String str;
        if (paramObject == null)
            str = "null";
        while (true)
        {
            label7: return str;
            if (paramObject.getClass().isPrimitive())
                str = paramObject.toString();
            JSONArray localJSONArray;
            StringBuilder localStringBuilder;
            int j;
            if (paramObject instanceof JSONArray)
            {
                localJSONArray = (JSONArray)paramObject;
                int i = localJSONArray.length();
                if (i == 0)
                    str = "";
                localStringBuilder = new StringBuilder();
                j = 0;
                label68: if ((j < i) && (j == 0));
            }
            try
            {
                localStringBuilder.append(",");
                localStringBuilder.append(getAsString(localJSONArray.get(j)));
                label104: ++j;
                break label68:
                str = localStringBuilder.toString();
                break label7:
                str = paramObject.toString();
            }
            catch (JSONException localJSONException)
            {
                break label104:
            }
        }
    }

    private static List<String> getSortedKeys(JSONObject paramJSONObject)
    {
        Iterator localIterator = paramJSONObject.keys();
        ArrayList localArrayList = new ArrayList();
        while (localIterator.hasNext())
            localArrayList.add(localIterator.next());
        Collections.sort(localArrayList);
        return localArrayList;
    }

    private void logJsonParseError()
    {
        EventLog localEventLog = new EventLog();
        localEventLog.action = "json_parse_error";
        localEventLog.searchKeyword = getHttpRequestUrl();
        d.a(localEventLog);
    }

    private void logRequestSignError()
    {
        EventLog localEventLog = new EventLog();
        localEventLog.action = "json_sign_error";
        localEventLog.searchKeyword = getHttpRequestUrl();
        localEventLog.page = "5000011";
        d.a(localEventLog);
    }

    public Object createRequestData()
    {
        JSONObject localJSONObject = new JSONObject();
        Iterator localIterator1 = this.mArgs.entrySet().iterator();
        while (localIterator1.hasNext())
        {
            Map.Entry localEntry = (Map.Entry)localIterator1.next();
            Object localObject1 = localEntry.getValue();
            if (localObject1 instanceof List)
            {
                Iterator localIterator2 = ((List)localObject1).iterator();
                ArrayList localArrayList = null;
                while (localIterator2.hasNext())
                {
                    Object localObject2 = localIterator2.next();
                    if (!localObject2 instanceof e)
                        continue;
                    if (localArrayList == null)
                        localArrayList = new ArrayList(((List)localObject1).size());
                    localArrayList.add(((e)localObject2).m_());
                }
                if ((localArrayList != null) && (!localArrayList.isEmpty()))
                {
                    JSONArray localJSONArray2 = new JSONArray(localArrayList);
                    localJSONObject.put((String)localEntry.getKey(), localJSONArray2);
                }
                JSONArray localJSONArray1 = new JSONArray((List)localObject1);
                localJSONObject.put((String)localEntry.getKey(), localJSONArray1);
            }
            if (localObject1 instanceof Map)
                localJSONObject.put((String)localEntry.getKey(), new JSONObject((Map)localObject1));
            String str = (String)localEntry.getKey();
            if ("opt_fields".equals(str))
                continue;
            localJSONObject.put(str, localObject1);
        }
        return localJSONObject;
    }

    protected JSONObject createRequestHeader(Object paramObject)
    {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("id", this.mRequestId);
        localJSONObject.put("client", generateClientJsonStr());
        localJSONObject.put("data", paramObject);
        return localJSONObject;
    }

    @Deprecated
    protected String generateMD5Key()
    {
        int i = 0;
        Iterator localIterator1 = this.mArgs.entrySet().iterator();
        int j = this.mArgs.size();
        String[] arrayOfString = new String[j];
        int k = 0;
        label36: Map.Entry localEntry;
        Object localObject1;
        StringBuilder localStringBuilder2;
        if (localIterator1.hasNext())
        {
            localEntry = (Map.Entry)localIterator1.next();
            localObject1 = localEntry.getValue();
            if (!localObject1 instanceof List)
                break label283;
            localStringBuilder2 = new StringBuilder();
            Iterator localIterator2 = ((List)localObject1).iterator();
            while (localIterator2.hasNext())
            {
                Object localObject3 = localIterator2.next();
                localStringBuilder2.append(localObject3.toString() + ",");
            }
            localStringBuilder2.setLength(-1 + localStringBuilder2.length());
        }
        for (Object localObject2 = localStringBuilder2.toString(); ; localObject2 = localObject1)
        {
            int l = k + 1;
            arrayOfString[k] = ((String)localEntry.getKey() + '=' + localObject2);
            k = l;
            break label36:
            Arrays.sort(arrayOfString, sComparator);
            StringBuilder localStringBuilder1 = new StringBuilder(j * 20);
            localStringBuilder1.append("secret.wdj.client");
            while (i < j)
            {
                localStringBuilder1.append(arrayOfString[i]);
                ++i;
            }
            localStringBuilder1.append("LVJd97AbRtikeYRRhi3ocdwSD");
            label283: return z.b(localStringBuilder1.toString());
        }
    }

    public String generateMD5Key2(Object paramObject)
    {
        StringBuilder localStringBuilder;
        if (paramObject instanceof JSONObject)
        {
            //传入的JSONobject对象
            JSONObject localJSONObject = (JSONObject)paramObject;
            localStringBuilder = new StringBuilder();
            //添加字符串
            localStringBuilder.append("secret.wdj.client");
            //List<String>
            Iterator localIterator = getSortedKeys(localJSONObject).iterator();
            while (localIterator.hasNext())
            {
                String str2 = (String)localIterator.next();
                String str3 = getAsString(localJSONObject.opt(str2));
                localStringBuilder.append(str2).append("=").append(str3);
            }
            localStringBuilder.append("LVJd97AbRtikeYRRhi3ocdwSD");
        }
        for (String str1 = z.b(localStringBuilder.toString()); ; str1 = "")
            return str1;
    }

    protected final String getABTestValue()
    {
        ArrayList localArrayList = new ArrayList();
        if (this.mExtraObject != null)
        {
            JSONObject localJSONObject = this.mExtraObject.optJSONObject("abtest");
            if (localJSONObject != null)
            {
                Iterator localIterator = localJSONObject.keys();
                while (localIterator.hasNext())
                    localArrayList.add(localIterator.next());
            }
        }
        if (i.a(localArrayList));
        for (String str = null; ; str = getABTestValue(localArrayList))
            return str;
    }

    protected String getABTestValue(String paramString)
    {
        String str1 = (String)this.mABTestMap.get(paramString);
        if (str1 == null)
        {
            StringBuilder localStringBuilder = new StringBuilder();
            try
            {
                if (this.mExtraObject == null)
                    break label144;
                JSONObject localJSONObject1 = this.mExtraObject.optJSONObject("abtest");
                if (localJSONObject1 == null)
                    break label144;
                JSONArray localJSONArray = localJSONObject1.optJSONArray(paramString);
                if (localJSONArray == null)
                    break label144;
                int i = 0;
                if (i >= localJSONArray.length())
                    break label144;
                JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
                String str2 = localJSONObject2.optString("experimentName");
                String str3 = localJSONObject2.optString("engagementName");
                if (i != 0)
                    localStringBuilder.append("&");
                localStringBuilder.append(str2).append(":").append(str3);
                label144: ++i;
            }
            catch (JSONException localJSONException)
            {
                this.mABTestMap.put(paramString, localStringBuilder.toString());
                str1 = localStringBuilder.toString();
            }
        }
        return str1;
    }

    protected final String getABTestValue(List<String> paramList)
    {
        if (i.a(paramList));
        StringBuilder localStringBuilder;
        for (String str = ""; ; str = localStringBuilder.toString())
        {
            return str;
            localStringBuilder = new StringBuilder();
            int i = paramList.size();
            for (int j = 0; j < i; ++j)
            {
                if (j > 0)
                    localStringBuilder.append("#");
                localStringBuilder.append(getABTestValue((String)paramList.get(j)));
            }
        }
    }

    public Map<String, Object> getArgs()
    {
        return this.mArgs;
    }

    public abstract String getHttpRequestApiName();

    public byte[] getRequestBytes()
    {
        onRequestStart(this.mArgs);
        try
        {
            String str1 = (String)this.mArgs.get("opt_fields");
            if (!TextUtils.isEmpty(str1))
                this.mArgs.remove("opt_fields");
            Object localObject2 = createRequestData();
            JSONObject localJSONObject = createRequestHeader(localObject2);
            if (isEncryptByM9())
            {
                //
                localJSONObject.put("sign", "");
                String str2 = localJSONObject.toString();
                ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
                localGZIPOutputStream.write(str2.getBytes("utf-8"));
                localGZIPOutputStream.flush();
                localGZIPOutputStream.close();
                byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
                localByteArrayOutputStream.close();
                if (arrayOfByte2 != null)
                    localObject1 = com.pp.assistant.w.b.a().b().a(arrayOfByte2);
            }
            else
            {
                //传入localObject2
                localJSONObject.put("sign", generateMD5Key2(localObject2));
                localJSONObject.put("encrypt", "md5");
                if (!TextUtils.isEmpty(str1))
                    localJSONObject.put("optFields", str1);
                byte[] arrayOfByte1 = localJSONObject.toString().getBytes();
                localObject1 = arrayOfByte1;
            }
        }
        catch (Exception localException)
        {
            localObject1 = null;
        }
        break label228:
        Object localObject1 = null;
        label228: return (B)localObject1;
    }

    public HttpBaseData getResultData(String paramString)
    {
        return (HttpBaseData)this.mGson.fromJson(paramString, getResultDataType());
    }

    public abstract Type getResultDataType();

    public HttpBaseData handleErrorData(HttpBaseData paramHttpBaseData)
    {
        return paramHttpBaseData;
    }

    public HttpBaseData handleResultData(HttpResultData paramHttpResultData)
    {
        if ((paramHttpResultData.isEmpty()) && (!needDeepParseData()));
        for (paramHttpResultData = new HttpErrorData(-1610612735); ; paramHttpResultData = new HttpErrorData(-1610612735))
            do
            {
                return paramHttpResultData;
                onLoadingSuccess(paramHttpResultData);
                n.a(getHttpRequestUrl(), paramHttpResultData.getRandomUrl());
            }
            while (!paramHttpResultData.isEmpty());
    }

    public abstract boolean isEncryptByM9();

    public boolean isNeedChannelId()
    {
        return false;
    }

    public boolean isNeedClientExArg()
    {
        return false;
    }

    public boolean needDeepParseData()
    {
        return false;
    }

    public abstract void onLoadingSuccess(HttpResultData paramHttpResultData);

    public void onParseResultDataSuccess(JSONObject paramJSONObject, String paramString, HttpBaseData paramHttpBaseData)
    {
    }

    public abstract void onRequestStart(Map<String, Object> paramMap);

    public HttpBaseData parseResultData(byte[] paramArrayOfByte)
    {
        JSONObject localJSONObject1 = new JSONObject(new String(paramArrayOfByte, "UTF-8"));
        JSONObject localJSONObject2 = (JSONObject)localJSONObject1.get("state");
        int i = ((Integer)localJSONObject2.get("code")).intValue();
        this.mExtraObject = localJSONObject1.optJSONObject("ex");
        String str1 = (String)localJSONObject2.opt("tips");
        if (i != 2000000)
            if (i == 5000011)
                logRequestSignError();
        HttpBaseData localHttpBaseData;
        for (Object localObject = new HttpErrorData(i, str1); ; localObject = localHttpBaseData)
        {
            String str2;
            while (true)
            {
                return localObject;
                str2 = localJSONObject1.get("data").toString();
                localHttpBaseData = getResultData(str2);
                if (localHttpBaseData.status == 0)
                    break;
                localObject = new HttpErrorData(localHttpBaseData.status, str1);
            }
            onParseResultDataSuccess(localJSONObject1, str2, localHttpBaseData);
        }
    }

    public void setABTestInfoForRecSet(PPAdBean paramPPAdBean)
    {
        ExRecommendSetBean localExRecommendSetBean = (ExRecommendSetBean)((AdExDataBean)paramPPAdBean).exData;
        List localList = localExRecommendSetBean.content;
        if (!i.b(paramPPAdBean.abTestNames))
            return;
        if (TextUtils.isEmpty(localExRecommendSetBean.abTestValue))
        {
            localExRecommendSetBean.abTestValue = getABTestValue(paramPPAdBean.abTestNames);
            localExRecommendSetBean.abtest = true;
        }
        if (!i.b(localList))
            return;
        Iterator localIterator1 = localList.iterator();
        ExRecommendSetAppBean localExRecommendSetAppBean1;
        do
        {
            if (!localIterator1.hasNext())
                return;
            localExRecommendSetAppBean1 = (ExRecommendSetAppBean)localIterator1.next();
            localExRecommendSetAppBean1.abTestValue = localExRecommendSetBean.abTestValue;
            localExRecommendSetAppBean1.abtest = true;
            localExRecommendSetAppBean1.installModule = this.mModuleName;
            localExRecommendSetAppBean1.installPage = this.mPageName;
        }
        while (!i.b(localExRecommendSetAppBean1.apps));
        Iterator localIterator2 = localExRecommendSetAppBean1.apps.iterator();
        while (true)
        {
            if (localIterator2.hasNext());
            ExRecommendSetAppBean localExRecommendSetAppBean2 = (ExRecommendSetAppBean)localIterator2.next();
            localExRecommendSetAppBean2.abTestValue = localExRecommendSetBean.abTestValue;
            localExRecommendSetAppBean2.abtest = true;
            localExRecommendSetAppBean2.installPage = this.mPageName;
            localExRecommendSetAppBean2.installModule = this.mModuleName;
        }
    }

    public JSONObject setClientArgs(JSONObject paramJSONObject, Context paramContext)
    {
        JSONObject localJSONObject = new JSONObject();
        String str;
        if (this.mCommandId == 219)
            str = v.u();
        while (true)
        {
            paramJSONObject.put("aid", str);
            localJSONObject.put("caller", "secret.wdj.client");
            localJSONObject.put("ex", paramJSONObject);
            localJSONObject.put("versionCode", com.lib.shell.pkg.utils.a.d(paramContext));
            localJSONObject.put("VName", com.lib.shell.pkg.utils.a.c(paramContext));
            localJSONObject.put("puid", v.r());
            try
            {
                localJSONObject.put("uuid", v.g(paramContext));
                label99: return localJSONObject;
                str = v.t();
            }
            catch (Exception localException)
            {
                break label99:
            }
        }
    }

    public void setClientExArgIfNeed(JSONObject paramJSONObject)
    {
    }

    public void setExtraJsonObject(JSONObject paramJSONObject)
    {
        this.mExtraObject = paramJSONObject;
    }

    public void setRequestId(long paramLong)
    {
        this.mRequestId = paramLong;
    }

    public HttpBaseData setResponseBytes(byte[] paramArrayOfByte)
    {
        Object localObject;
        try
        {
            HttpBaseData localHttpBaseData1 = parseResultData(paramArrayOfByte);
            if (localHttpBaseData1 instanceof HttpResultData)
            {
                localObject = handleResultData((HttpResultData)localHttpBaseData1);
            }
            else
            {
                HttpBaseData localHttpBaseData2 = handleErrorData(localHttpBaseData1);
                localObject = localHttpBaseData2;
            }
        }
        catch (Exception localException)
        {
            logJsonParseError();
            localObject = new HttpErrorData(-1610612729);
        }
        return (HttpBaseData)localObject;
    }
}